package com.ticketTrade.ticketTrade.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ticketTrade.ticketTrade.Model.Ticket;
import com.ticketTrade.ticketTrade.Model.User;
import com.ticketTrade.ticketTrade.Service.Ticketservice;
@RequestMapping("/api")
@RestController
public class Controller {
	
	@Autowired
	private Ticketservice service;
	
	
	@GetMapping("/ticket")          
	public List<Ticket>getallproduct()
	{
		return service.getallproduct();
		
	}
	@GetMapping("/ticket/{id}")
	public Ticket getproduct(@PathVariable int id)
	{
		return service.getproductbid(id);
	}
	@PostMapping("/ticket")
	public Ticket addproduct(@RequestPart Ticket product , @RequestPart MultipartFile image) throws IOException
	{
		return service.addproduct(product, image);
	}
	
	
	//
	
	
	@GetMapping("/ticket/{ticketid}/image")
	public ResponseEntity<byte[]> getimagebyticketid(@PathVariable int ticketid )  //pathvariable for accepting image and byte because photo will be in byte format
	{
		Ticket ticket=service.getproductbid(ticketid);
		byte[] image= ticket.getImagedata();
			
		return ResponseEntity.ok().contentType(MediaType.valueOf(ticket.getImagetype())).body(image);
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<String>updateproduct(@PathVariable int id, 
			@RequestPart Ticket product , @RequestPart MultipartFile image) throws IOException
	{
		Ticket ticket1=service.updateproduct(id,product,image);
		if(ticket1!=null)
		
			return new ResponseEntity<>("done", HttpStatus.OK);
		else
			return new ResponseEntity<>("problem", HttpStatus.BAD_REQUEST);
		
		
	}
	@DeleteMapping("/ticket/{id}")
	public ResponseEntity<String>deleteproduct(@PathVariable int id)
	{
		Ticket ticket=service.getproductbid(id);
		if(ticket!=null)
		{
		service.deleteproduct(id);
			return new ResponseEntity<>("deleted",HttpStatus.ACCEPTED);
		}
		else
			return new ResponseEntity<>("problem occured",HttpStatus.BAD_REQUEST);
	}
	@GetMapping("/products/search")
	public ResponseEntity<List<Ticket>>searchproduct(@RequestParam String keyword)
	{
		List<Ticket> ticket=service.searchproduct(keyword);
		return new ResponseEntity<>(ticket,HttpStatus.OK);
	}
	@PostMapping("/login")
	public ResponseEntity<?> log(@RequestBody User user)
	{
		return service.verify(user);
	}
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user) {
	    try {
	        User registeredUser = service.adduservice(user);
	        return ResponseEntity.ok(registeredUser);
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	    
	}
	
	
	
	
	

}
