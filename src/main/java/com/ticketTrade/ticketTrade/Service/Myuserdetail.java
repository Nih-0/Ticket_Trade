package com.ticketTrade.ticketTrade.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ticketTrade.ticketTrade.Model.User;
import com.ticketTrade.ticketTrade.Model.Userprincipal;
import com.ticketTrade.ticketTrade.Repo.UserRepository;

@Service
public class Myuserdetail implements UserDetailsService {

	@Autowired
	private UserRepository repo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=repo.findByUsername(username);
		if(user==null)
		{
			System.out.println("user not found");
			throw new UsernameNotFoundException("user not found");
		}
		return new Userprincipal(user);
	}

}
