package com.auction.auction_site.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 1000)
    private String description;
   
    @Column(nullable = false)
    private String imageUrl;
    
    @Column(nullable = false)
    private Double startingPrice;

    private Double currentPrice;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuctionStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
   @JsonIgnoreProperties({"password", "email"}) // למנוע לולאות אינסופיות
    private User seller;


    @OneToMany(mappedBy = "items", cascade = CascadeType.ALL)
    private List<Offers> offers;
    
    
    @OneToOne
    @JoinColumn(name = "winner_offer_id")
    private Offers winnerOffer;

   
    public Items() {
    	this.status = AuctionStatus.active;
    }

    public Items(String title, String description, Double startingPrice, LocalDateTime startDate, LocalDateTime endDate, User seller) {
        this.title = title;
        this.description = description;
        this.startingPrice = startingPrice;
        this.currentPrice = startingPrice; 
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = AuctionStatus.active;
        this.seller = seller;
    }
    public void setTitle(String title){
    	this.title = title;
    	}
    public String getTitle(){
    	return title;
    	}
  
  
    public void setImageUrl(String imageUrl){
    	this.imageUrl = imageUrl;
    	}
    
    public String getImageUrl() {
    	return imageUrl;
    }
    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }
    public void setStatus() {
        this.status = AuctionStatus.closed;
    }

    public AuctionStatus getStatus() {
        return status;
    }
    public String getDescription() {
    	return description; 
    	}
    
    public void setDescription(String description){
    	this.description = description;
    	}
    public void setStartingPrice(Double startingPrice) {
        this.startingPrice = startingPrice;
        this.currentPrice = startingPrice; 

    }
    public Double getStartingPrice() {
    	return startingPrice;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
    public LocalDateTime getStartDate() {
    	return startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
    public LocalDateTime getEndDate() {
    	return endDate;
    }
    public void setSeller(User seller) {
        this.seller = seller;
    }
    public User getSeller()
    {
    	return this.seller; 
    	}
    	public Long getId() {
    
    	return id;
    }
    	public List<Offers> getOffers() {
    	    return offers;
    	}
    public Offers getWinnerOffer() {
    	        return winnerOffer;
    	    }

     public void setWinnerOffer(Offers winnerOffer) {
    	        this.winnerOffer = winnerOffer;
    	    }
}



