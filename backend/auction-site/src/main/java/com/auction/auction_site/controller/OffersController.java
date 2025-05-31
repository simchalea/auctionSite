package com.auction.auction_site.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.auction.auction_site.model.Offers;
import com.auction.auction_site.model.User;
import com.auction.auction_site.repository.UserRepository;
import com.auction.auction_site.service.OffersService;

import auctionSite.dto.OfferRequest;

@RestController
@RequestMapping("/api/offers")

public class OffersController {
	 @Autowired
	 private OffersService offersService;
	 @Autowired
	 UserRepository userRepository;
	 @PostMapping
	 public ResponseEntity<?> addOffer(@RequestBody OfferRequest offerDto) {
	     if (offerDto.getBidderId() == null || offerDto.getItemId() == null || offerDto.getAmount() == null) {
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing bidder ID, item ID or amount.");
	     }

	     try {
	         User bidder = userRepository.findById(offerDto.getBidderId())
	                 .orElseThrow(() -> new RuntimeException("User not found"));

	         Offers savedOffer = offersService.placeOffer(
	                 offerDto.getItemId(),
	                 bidder,
	                 offerDto.getAmount()
	         );

	         return ResponseEntity.ok(savedOffer);
	     } catch (IllegalArgumentException e) {
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	     } catch (RuntimeException e) {
	         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to place offer.");
	     }
	 }

	 @GetMapping("/item/{itemId}")
	 public ResponseEntity<List<Offers>> getOffersByItem(@PathVariable Long itemId) {
	     List<Offers> offers = offersService.getOffersByItemId(itemId);
	     return ResponseEntity.ok(offers);
	 }
}
