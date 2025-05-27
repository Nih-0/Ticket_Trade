package com.ticketTrade.ticketTrade.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketTrade.ticketTrade.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	    User findByUsername(String username);
	}


