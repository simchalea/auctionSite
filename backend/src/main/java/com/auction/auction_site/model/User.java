package com.auction.auction_site.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String phone;
    
    @Column(nullable = false)
    private LocalDateTime date;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {  
        this.id = id;
    }

	public String getPassword() {
		
		return password;
	}

	public void setPassword(String password) {
		this.password=password;
		
	}
public String getPhone() {
		
		return phone;
	}

	public void setPhone(String phone) {
		this.phone=phone;
		
	}
public void setEmail(String email) {
		
		this.email=email;
	}
	
	public String getEmail() {
		
		return email;
	}
	public void setDate(LocalDateTime date) {
    this.date = date;
	}
}
