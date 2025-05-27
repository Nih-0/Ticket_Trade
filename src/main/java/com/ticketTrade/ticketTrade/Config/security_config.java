package com.ticketTrade.ticketTrade.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class security_config {
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private jwtFilter jwtfilter;
	
	@Bean
	public SecurityFilterChain sec(HttpSecurity http) throws Exception
	{
		http.csrf(Customizer->Customizer.disable());
		http.authorizeHttpRequests(req -> req
				.requestMatchers("api/register", "api/login")
				.permitAll()
				.anyRequest().authenticated());
		http.oauth2Login(Customizer.withDefaults());

		//http.formLogin(Customizer.withDefaults());
		http.httpBasic(Customizer.withDefaults());
		http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
//	@Bean  //here also we are hardcoding the values so not a optimal solution
//	public UserDetailsService userDetailsService()  //it is an interface so we have return obj of inmemory because it implements it
//	{
//		UserDetails user1=User
//				.withDefaultPasswordEncoder()
//				.username("nihal")
//				.password("nihaly")
//				.build();
//		UserDetails user2=User
//				.withDefaultPasswordEncoder()
//				.username("athul")
//				.password("ak")
//				.build();
//				return new InMemoryUserDetailsManager(user1,user2); // it is a varoc we can pass multiple using comma
//		
//	}
	
	@Bean
	public AuthenticationProvider auth()
	{
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	
	@Bean
	public AuthenticationManager authmanager(AuthenticationConfiguration config) throws Exception
	{
		return config.getAuthenticationManager();
	}

}
