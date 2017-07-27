package com.discussion.portal.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.discussion.portal.model.User;
import com.discussion.portal.mongodb.model.DbUser;

/**
 * 
 * @author Vishal
 *
 */
@Component
public class UserUtils {
	
	public String getCurrentUser() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	public User convertDbUserToUser(DbUser dbUser) {
		
		User user = new User();
		user.setUsername(dbUser.getUsername());
		user.setQuestionId(dbUser.getQuestionId());
		user.setUserAnswerMap(dbUser.getUserAnswerMap());
		
		return user;
	}
}
