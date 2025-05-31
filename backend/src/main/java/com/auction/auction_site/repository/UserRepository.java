package com.auction.auction_site.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auction.auction_site.model.User;

public interface UserRepository extends JpaRepository<User, Long>  {
	Optional<User> findByEmail(String email);
	

}
