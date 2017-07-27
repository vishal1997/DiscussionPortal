package com.discussion.portal.dao;

import java.util.List;

import com.discussion.portal.mongodb.model.DbQuestion;

public interface PortalDao {

	public String insertQuestion(DbQuestion question);
	public DbQuestion findQuestion(String question);
	public List<String> getQuestionIdsByUserId(String userId);
	
}
