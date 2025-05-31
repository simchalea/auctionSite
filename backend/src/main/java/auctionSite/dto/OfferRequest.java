package auctionSite.dto;

public class OfferRequest {
	
	    private Double amount;
	    private Long itemId;
	    private Long bidderId;
	    
	    public Double getAmount() {
	        return amount;
	    }

	    public void setAmount(Double amount) {
	        this.amount = amount;
	    }

	    public Long getItemId() {
	        return itemId;
	    }

	    public void setItemId(Long itemId) {
	        this.itemId = itemId;
	    }
	    public Long getBidderId() {
	        return bidderId;
	    }
	    public void setBidderId(Long bidderId) {
	        this.bidderId = bidderId;
	    }


}
