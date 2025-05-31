package auctionSite.dto;

public class UserDto {
	  private String username;
	    private String phone;

	    public UserDto(String username, String phone) {
	        this.username = username;
	        this.phone = phone;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public String getPhone() {
	        return phone;
	    }
	}