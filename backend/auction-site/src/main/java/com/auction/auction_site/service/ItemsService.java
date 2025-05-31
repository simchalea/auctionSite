package com.auction.auction_site.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.auction.auction_site.model.*;
import com.auction.auction_site.repository.ItemsRepository;

import jakarta.transaction.Transactional;

import com.auction.auction_site.model.AuctionStatus;
@Service
public class ItemsService {
    @Autowired
    private ItemsRepository itemsRepository;
   
    public ItemsService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

	/*
	 * public Items createItem(Items item) { return itemsRepository.save(item); }
	 */
    public Items createItemWithImage(String title, String description, Double startingPrice,
            LocalDateTime startDate, LocalDateTime endDate,
            Long sellerId, MultipartFile imageFile) throws IOException {

// שמירת הקובץ לדיסק
String uploadsDir = "uploads/";
File uploadFolder = new File(uploadsDir);
if (!uploadFolder.exists()) {
uploadFolder.mkdirs();
}

String originalFilename = imageFile.getOriginalFilename();
String uniqueFilename = UUID.randomUUID() + "_" + originalFilename;
Path filePath = Paths.get(uploadsDir, uniqueFilename);
Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

// יצירת האובייקט Items
Items item = new Items();
item.setTitle(title);
item.setDescription(description);
item.setStartingPrice(startingPrice);
item.setCurrentPrice(startingPrice);
item.setStartDate(startDate);
item.setEndDate(endDate);
item.setImageUrl("/"+uploadsDir + uniqueFilename); // כדי שתוכלי לגשת אליו דרך ה־Frontend

User seller = new User();
seller.setId(sellerId);
item.setSeller(seller);

return itemsRepository.save(item);
}


/*
 * public List<Items> getAllItems() {
 * 
 * return itemsRepository.findByStatus(AuctionStatus.active); }
 */
    public Optional<Items> getItemById(Long id) {
        return itemsRepository.findById(id);
    }

	/*
	 * public void updateStatus(Long id) { Items item = itemsRepository.findById(id)
	 * .orElseThrow(() -> new RuntimeException("Auction not found"));
	 * 
	 * item.setStatus(); itemsRepository.save(item); }
	 */
    public List<Items> getActiveItems() {
    	
    	List<Items> activeItems = itemsRepository.findByStatusOrderByStartDateDesc(AuctionStatus.active);
        return activeItems;
    }
    public boolean updateEndDate(Long itemId, LocalDate newEndDate) {
        Optional<Items> optionalItem = itemsRepository.findById(itemId);
        if (optionalItem.isPresent()) {
            Items item = optionalItem.get();

            // תנאי 1: הפריט חייב להיות במצב פעיל
            if (item.getStatus() != AuctionStatus.active) {
                return false;
            }

            // תנאי 2: אי אפשר להחזיר את הזמן לאחור
            if (newEndDate.isBefore(LocalDate.now())) {
                return false;
            }

            // תנאי 3: אי אפשר לעדכן אם התאריך הנוכחי כבר אחרי תאריך הסיום
            if (LocalDateTime.now().isAfter(item.getEndDate())) {
                return false;
            }

            // תנאי 4: בדיקה שאין הצעה שנבחרה כזוכה (אם יש לוגיקה כזו – ניתן לבדוק ידנית)
            if (item.getOffers() != null && !item.getOffers().isEmpty()) {
                // לדוגמה: נניח שהצעה זוכה היא ההצעה עם המחיר הגבוה ביותר בתום הזמן
                Offers highest = item.getOffers().stream()
                    .max(Comparator.comparing(Offers::getAmount))
                    .orElse(null);

                if (highest != null && LocalDateTime.now().isAfter(item.getEndDate())) {
                    return false; // יש זוכה והמכרז הסתיים
                }
            }

            // כל התנאים עברו – ניתן לעדכן
            item.setEndDate(newEndDate.atStartOfDay());
            itemsRepository.save(item);
            return true;
        }
        return false;
    }
    @Transactional
    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Jerusalem") // כל יום ב-00:00 בלילה
    public void finalizeExpiredAuctions() {
        List<Items> activeItems = itemsRepository.findByStatusWithOffers(AuctionStatus.active);

        for (Items item : activeItems) {
            if (item.getEndDate().isBefore(LocalDateTime.now())) {
                Optional<Offers> highest = item.getOffers().stream()
                    .max(Comparator.comparing(Offers::getAmount));

                highest.ifPresent(item::setWinnerOffer);
                item.setStatus();
                itemsRepository.save(item);
            }
        }

        System.out.println("Finalized expired auctions at: " + LocalDateTime.now());
    }


}

