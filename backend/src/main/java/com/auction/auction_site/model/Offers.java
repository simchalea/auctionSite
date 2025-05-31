package com.auction.auction_site.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "offers")
public class Offers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDateTime offerTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User bidder;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    @JsonBackReference
    private Items items;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getOfferTime() {
        return offerTime;
    }

    public void setOfferTime(LocalDateTime offerTime) {
        this.offerTime = offerTime;
    }

    public User getBidder() {
        return bidder;
    }

    public void setBidder(User bidder) {
        this.bidder = bidder;
    }

    public Items getItems() {
        return items;
    }
    public void setItems(Items items) {
    	this.items=items;
		
	}
}
