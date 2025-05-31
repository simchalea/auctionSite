package com.auction.auction_site.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auction.auction_site.model.AuctionStatus;
import com.auction.auction_site.model.Items;
import com.auction.auction_site.model.User;
import com.auction.auction_site.repository.ItemsRepository;
import com.auction.auction_site.repository.UserRepository;
import com.auction.auction_site.service.UserService;

import auctionSite.dto.AuctionWithUsersDto;
import auctionSite.dto.LoginRequest;
//import auctionSite.dto.LoginResponse;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemsRepository itemsRepository;

    // Register a new user
    
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    	System.out.println("Email from request: " + request.getEmail()+"  "+ request.getPassword());
    	User user = userRepository.findByEmail(request.getEmail())
    		    .orElse(null);

    		if (user == null || !user.getPassword().equals(request.getPassword())) {
    			 System.out.println("❌ Password does not match");
    		    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    		}
    		
    		return ResponseEntity.ok(user);
    }
    @GetMapping("/{id}/auctions")
    public ResponseEntity<?> getUserAuctions(@PathVariable Long id) {
       
		List<Items> all = itemsRepository.findBySellerId(id);
        Map<String, List<Items>> response = new HashMap<>();
        response.put("open", all.stream().filter(i -> i.getStatus()==AuctionStatus.active).toList());
        response.put("closed", all.stream().filter(i -> i.getStatus()==AuctionStatus.closed).toList());
        return ResponseEntity.ok(response);
    }
 
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        try {
            String password = payload.get("password");
            if (password == null || password.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Password must not be empty");
            }

            User user = userRepository.findById(id).orElseThrow();
            user.setPassword(password);
            userRepository.save(user);
            return ResponseEntity.ok("User updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user");
        }
    }

	/*
	 * // Get user by ID
	 * 
	 * @GetMapping("/{id}") public ResponseEntity<?> getUserById(@PathVariable Long
	 * id) { return userService.getUserById(id) .map(ResponseEntity::ok)
	 * .orElse(ResponseEntity.notFound().build()); }
	 * 
	 * 
	 * @GetMapping public List<User> getAllUsers() { return
	 * userService.getAllUsers();
	 * 
	 * }
	 */
    @GetMapping("/{id}/closed-auctions")
    public ResponseEntity<List<AuctionWithUsersDto>> getClosedAuctionsWithDetails(@PathVariable Long id) {
        List<AuctionWithUsersDto> data = userService.getClosedAuctionsWithUserDetails(id);
        return ResponseEntity.ok(data);
    }
    @GetMapping("/{id}/won-auctions")
    public ResponseEntity<List<AuctionWithUsersDto>> getWonAuctions(@PathVariable Long id) {
        List<AuctionWithUsersDto> result = userService.getWonAuctionsWithSellerDetails(id);
        return ResponseEntity.ok(result);
    }

}
