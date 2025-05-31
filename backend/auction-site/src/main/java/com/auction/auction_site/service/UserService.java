package com.auction.auction_site.service;

import java.util.List;
//import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auction.auction_site.model.*;
import com.auction.auction_site.repository.ItemsRepository;
import com.auction.auction_site.repository.UserRepository;

import auctionSite.dto.AuctionWithUsersDto;
import auctionSite.dto.UserDto;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemsRepository itemsRepository;
    
    public User saveUser(User user) {
        return userRepository.save(user);
    }

	/*
	 * public Optional<User> getUserById(Long id) { return
	 * userRepository.findById(id); }
	 * 
	 * public List<User> getAllUsers() { return userRepository.findAll(); }
	 */
    public List<AuctionWithUsersDto> getClosedAuctionsWithUserDetails(Long userId) {
        List<Items> closedAuctions = itemsRepository.findBySellerId(userId).stream()
            .filter(item -> item.getStatus()==AuctionStatus.closed)
            .collect(Collectors.toList());

        return closedAuctions.stream()
            .map(item -> {
                Offers winnerOffer = item.getWinnerOffer();
                UserDto winner = null;
                if (winnerOffer != null && winnerOffer.getBidder() != null) {
                    winner = new UserDto(winnerOffer.getBidder().getEmail(), winnerOffer.getBidder().getPhone());
                }
                UserDto seller = new UserDto(item.getSeller().getEmail(), item.getSeller().getPhone());
                return new AuctionWithUsersDto(item.getTitle(), item.getCurrentPrice(), item.getEndDate().toString(), winner, seller);
            })
            .collect(Collectors.toList());
    }
    
    public List<AuctionWithUsersDto> getWonAuctionsWithSellerDetails(Long userId) {
        List<Items> wonAuctions = itemsRepository.findAll().stream()
            .filter(item -> item.getStatus()==AuctionStatus.closed)
            .filter(item -> item.getWinnerOffer() != null && item.getWinnerOffer().getBidder().getId().equals(userId))
            .collect(Collectors.toList());

        return wonAuctions.stream()
            .map(item -> {
                Offers winnerOffer = item.getWinnerOffer();
                UserDto winner = new UserDto(winnerOffer.getBidder().getEmail(), winnerOffer.getBidder().getPhone());
                UserDto seller = new UserDto(item.getSeller().getEmail(), item.getSeller().getPhone());
                return new AuctionWithUsersDto(
                    item.getTitle(),
                    item.getCurrentPrice(),
                    item.getEndDate().toString(),
                    winner,
                    seller
                );
            })
            .collect(Collectors.toList());
    }


}