package auctionSite.dto;

public class AuctionWithUsersDto {
    private String title;
    private Double currentPrice;
    private String endDate;
    private UserDto winner;
    private UserDto seller;

    public AuctionWithUsersDto(String title, Double currentPrice, String endDate,UserDto winner, UserDto seller) {
        this.title = title;
        this.currentPrice = currentPrice;
        this.endDate=endDate;
        this.winner = winner;
        this.seller = seller;
    }

    public String getTitle() {
        return title;
    }
    public String getEndDate() {
        return endDate;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public UserDto getWinner() {
        return winner;
    }

    public UserDto getSeller() {
        return seller;
    }
}