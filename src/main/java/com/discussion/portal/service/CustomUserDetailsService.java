package com.discussion.portal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.discussion.portal.mongodb.model.DbUser;
import com.discussion.portal.mongodb.repository.UserRepository;
import com.discussion.portal.utils.Json;

/**
 * 
 * @author Vishal
 *
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<DbUser> dbUser = userRepo.findByUsername(username);
		
		System.out.println(username);
		System.out.println(Json.toJson(dbUser));
		
		dbUser 
			.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		return dbUser
			.map(CustomUserDetails::new).get();
	}
}
