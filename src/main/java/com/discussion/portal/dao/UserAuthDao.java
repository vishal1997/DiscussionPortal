package com.discussion.portal.dao;

import java.util.List;

import com.discussion.portal.mongodb.model.DbUser;
import com.fasterxml.jackson.core.JsonProcessingException;


/**
 * 
 * @author Vishal
 *
 */
public interface UserAuthDao {

	public String createUser(String userId) throws JsonProcessingException;
	
	public String updateUserQuestion(String questionId, String userId);
	
	public String registerUser(DbUser dbUser);
	
	public String deleteAnswerToMap(String questionId, String userId);
	
	public String resetPassword(String userId, String password);
	
	public String resetEmailId(String userId, String emailId);

	public List<DbUser> getUserByName(String name);
}
