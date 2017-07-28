package com.discussion.portal.dao;

import java.util.List;

import com.discussion.portal.model.Answer;
import com.discussion.portal.mongodb.model.DbAnswer;
import com.discussion.portal.mongodb.model.DbQuestion;
import com.discussion.portal.mongodb.model.DbUser;

public interface PortalDao {

	public String insertQuestion(DbQuestion question);
	public DbQuestion getQuestionById(String question);
	public List<String> getQuestionIdsByUserId(String userId);
	
	public String addAnswer(DbAnswer answer);
	
	public DbAnswer getAnswerById(String answerId);
	
}
