package com.ticketTrade.ticketTrade.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import com.ticketTrade.ticketTrade.Model.Ticket;
import com.ticketTrade.ticketTrade.Model.User;
import com.ticketTrade.ticketTrade.Repo.Ticketrepo;
import com.ticketTrade.ticketTrade.Repo.UserRepository;

@Service
public class Ticketservice {
	
	private  BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
	
	@Autowired
    private AuthenticationManager authManager;
    
    @Autowired
    private JWTservice jwtservice;
	
	@Autowired
	private Ticketrepo repo;
	@Autowired
	private UserRepository repo1;

	public List<Ticket> getallproduct() {
		
		return repo.findAll();
	}
	

	public Ticket addproduct(Ticket product, MultipartFile image)throws IOException {
		product.setImagename(image.getOriginalFilename());
	    product.setImagetype(image.getContentType());
	    product.setImagedata(image.getBytes());
	    
	    return repo.save(product);
	}


	public Ticket getproductbid(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}


	public Ticket updateproduct(int id, Ticket product, MultipartFile image) throws IOException {
		// TODO Auto-generated method stub
		product.setImagedata(image.getBytes());
		product.setImagename(image.getOriginalFilename());
		product.setImagetype(image.getContentType());
		return repo.save(product);
	}


	public void deleteproduct(int id) {
		repo.deleteById(id) ;
		
	}


	public List<Ticket> searchproduct(String keyword) {
		// TODO Auto-generated method stub
		return repo.searchproduct(keyword);
	}


	public User adduservice(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return repo1.save(user);
	}



	public ResponseEntity<?> verify(@RequestBody User user) {
	    try {
	        Authentication authentication = authManager.authenticate(
	            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
	        );
	        if (authentication.isAuthenticated()) {
	            String token = jwtservice.generatetoken(user.getUsername());
	            return ResponseEntity.ok(Map.of("token", token));
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	    }
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
	}

	}


