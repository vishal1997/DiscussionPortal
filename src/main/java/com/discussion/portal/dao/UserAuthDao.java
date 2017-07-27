package com.discussion.portal.dao;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserAuthDao {

	public String createUser(String userId) throws JsonProcessingException;
	
	public String updateUserQuestion(String questionId, String userId);

}
