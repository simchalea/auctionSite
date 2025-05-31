package com.auction.auction_site.repository;

import com.auction.auction_site.model.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffersRepository extends JpaRepository<Offers, Long> {
	 // List<Offers> findByItems_Id(Long itemId);
	  List<Offers> findByItemsIdOrderByAmountDesc(Long itemId);
}

