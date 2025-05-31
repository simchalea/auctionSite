package auctionSite.dto;

import com.auction.auction_site.model.User;

public class LoginResponse {
    private String email;
    private Long id;
    private String phone;
    
    public LoginResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        }

    public String getUsername() {
        return email;
    }

    public Long getId() {
        return id;
    }
    public String getPhone() {
    	return phone;
    }
}
