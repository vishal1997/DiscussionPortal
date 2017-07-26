package com.discussion.portal.dao;

import com.discussion.portal.mongodb.model.DbUser;

public interface UserAuthDao {

	public String authUser(String username, String password);
	
	public DbUser getUserByUsername(String username);
	
	public String createUser(String username, String password);
	
	public String comparePassword(String Password);
}
