package com.auction.auction_site.service;

import com.auction.auction_site.model.Items;
import com.auction.auction_site.model.Offers;
import com.auction.auction_site.model.User;
import com.auction.auction_site.repository.ItemsRepository;
import com.auction.auction_site.repository.OffersRepository;
//import com.auction.auction_site.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OffersService {

    @Autowired
    private OffersRepository offersRepository;

    @Autowired
    private ItemsRepository itemsRepository;
   
    
    @Transactional
    public Offers placeOffer(Long itemId, User bidder, Double amount) {
        Items item = itemsRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        double minIncrement = getMinimumIncrement(item.getStartingPrice()); 
        double currentPrice = item.getCurrentPrice();
        if( amount < currentPrice + minIncrement) { throw new
       	  IllegalArgumentException("הצעה חייבת להיות גבוהה בלפחות " + minIncrement +
       	  " ₪ מהמחיר הנוכחי."); }

        // יצירת הצעה חדשה
        Offers newOffer = new Offers();
        newOffer.setAmount(amount);
        newOffer.setOfferTime(LocalDateTime.now());
        newOffer.setBidder(bidder);
        
         newOffer.setItems(item);

        // עדכון המיר
        item.setCurrentPrice(amount);
        itemsRepository.save(item); // שמירה

        return offersRepository.save(newOffer);
    }

	/*
	 * public List<Offers> getOfferssForItems(Long itemId) { return
	 * offersRepository.findByItems_Id(itemId); } public Offers saveOffer(Offers
	 * offer) { Items item = itemsRepository.findById(offer.getItems().getId())
	 * .orElseThrow(() -> new RuntimeException("Item not found")); User user =
	 * userRepository.findById(offer.getBidder().getId()) .orElseThrow(() -> new
	 * RuntimeException("User not found"));
	 * 
	 * offer.setItems(item); offer.setBidder(user);
	 * 
	 * double minIncrement = getMinimumIncrement(item.getStartingPrice()); double
	 * currentPrice = item.getCurrentPrice();
	 * 
	 * if (offer.getAmount() < currentPrice + minIncrement) { throw new
	 * IllegalArgumentException("הצעה חייבת להיות גבוהה בלפחות " + minIncrement +
	 * " ₪ מהמחיר הנוכחי."); }
	 * 
	 * // עדכון מחיר נוכחי item.setCurrentPrice(offer.getAmount());
	 * itemsRepository.save(item);
	 * 
	 * // זמן הצעה offer.setOfferTime(LocalDateTime.now());
	 * 
	 * return offersRepository.save(offer); }
	 */
    private double getMinimumIncrement(double startingPrice) {
    	if (startingPrice < 10) {
            return 2;
    	} else if (startingPrice < 100) {
            return 5;
        } else if (startingPrice < 1000) {
            return 10;
        } else if (startingPrice < 5000) {
            return 50;
        } else {
            return 100;
        }
    }
    
    public List<Offers> getOffersByItemId(Long itemId) {
        return offersRepository.findByItemsIdOrderByAmountDesc(itemId);
    }

}
