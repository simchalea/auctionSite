package com.auction.auction_site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.auction.auction_site.model.Items;
import com.auction.auction_site.service.ItemsService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/items")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

	/*
	 * @GetMapping public List<Items> getAllItems() { return
	 * itemsService.getAllItems(); }
	 */
    @GetMapping("/active")
    public List<Items> getActiveItems() {
        return itemsService.getActiveItems();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Items> getItemById(@PathVariable Long id) {
        return itemsService.getItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}/end-date")
    public ResponseEntity<?> updateEndDate(@PathVariable Long id, @RequestBody Map<String, String> request) {
        try {
        	System.out.println("Received endDate: " + request.get("endDate"));
            LocalDate newEndDate = LocalDate.parse(request.get("endDate"));
            boolean updated = itemsService.updateEndDate(id, newEndDate);
            if (updated) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot update end date. Auction is closed or invalid.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid date format.");
        }
    }

	/*
	 * @PostMapping public Items createItem(@RequestBody Items item) {
	 * System.out.println("Received imageUrl: " + item.getImageUrl()); return
	 * itemsService.createItem(item); }
	 */
	/*
	 * @PutMapping("/{id}/status") public ResponseEntity<Void>
	 * updateStatus(@PathVariable Long id) { itemsService.updateStatus(id); return
	 * ResponseEntity.ok().build(); }
	 */

    @PostMapping("/upload")
    public ResponseEntity<?> uploadItem(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("startingPrice") Double startingPrice,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam("sellerId") Long sellerId,
            @RequestParam("image") MultipartFile imageFile
    ) {
        try {
            Items item = itemsService.createItemWithImage(title, description, startingPrice, startDate, endDate, sellerId, imageFile);
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create item: " + e.getMessage());
        }
    }

}