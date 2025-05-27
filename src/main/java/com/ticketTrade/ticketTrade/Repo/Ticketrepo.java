package com.ticketTrade.ticketTrade.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ticketTrade.ticketTrade.Model.Ticket;

@Repository
public interface Ticketrepo extends JpaRepository<Ticket, Integer> {
	
	 @Query("SELECT p FROM Ticket p WHERE " +
	           "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	           "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	           "LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	    List<Ticket> searchproduct(String keyword);

	 

}
