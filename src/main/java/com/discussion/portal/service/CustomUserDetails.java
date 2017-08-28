package com.discussion.portal.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.discussion.portal.mongodb.model.DbUser;

public class CustomUserDetails extends DbUser implements UserDetails {

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	public CustomUserDetails(DbUser dbUser) {
		this.setPassword(dbUser.getPassword());
		this.setUsername(dbUser.getUsername());
	}
	
	public String getPassword() {
		return super.getPassword();
	}
	
	public String getUsername() {
		return super.getUsername();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	

}
