package com.discussion.portal.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.discussion.portal.dao.UserAuthDao;
import com.discussion.portal.mongodb.model.DbUser;
import com.discussion.portal.mongodb.repository.UserRepository;

@Component
public class UserAuthDaoImpl implements UserAuthDao {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public String authUser(String username, String password) {
		// TODO Auto-generated method stub
		return "Successfully Authenticated";
	}

	@Override
	public DbUser getUserByUsername(String username) {
		DbUser user = null;
		try {
			user = userRepository.findOne(username);
		} catch (Exception e) {
			throw new RuntimeException("Unable to find", e);
		}
		return user;
	}

	@Override
	public String createUser(String username, String password) {
		try {
			
			DbUser user = new DbUser();
			user.setPassword(password.hashCode());
			user.setUsername(username);
			userRepository.insert(user);
			
		} catch (Exception e) {
			throw new RuntimeException("Unable to create", e);
		}
		return "Success";
	}

	@Override
	public String comparePassword(String Password) {
		// TODO Auto-generated method stub
		return null;
	}

}
