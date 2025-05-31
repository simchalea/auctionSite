package com.auction.auction_site.repository;
import com.auction.auction_site.model.Items;
import com.auction.auction_site.model.Offers;
import com.auction.auction_site.model.AuctionStatus;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface ItemsRepository extends JpaRepository<Items, Long> {
 
	//List<Items> findByStatus(AuctionStatus status);
	List<Items> findByStatusOrderByStartDateDesc(AuctionStatus status);
	 List<Items> findBySellerId(Long sellerId);
	Optional<Items> findByWinnerOffer(Offers offer);
	@Query("SELECT i FROM Items i LEFT JOIN FETCH i.offers WHERE i.status = :status")
	List<Items> findByStatusWithOffers(@Param("status") AuctionStatus status);

	

}
