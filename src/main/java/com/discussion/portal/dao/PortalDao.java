package com.discussion.portal.dao;

import com.discussion.portal.mongodb.model.DbQuestion;

public interface PortalDao {

	public String insertQuestion(DbQuestion question);
	public DbQuestion findQuestion(String question);
}
