package com.discussion.portal.dao;

import java.util.List;

import com.discussion.portal.mongodb.model.DbAnswer;
import com.discussion.portal.mongodb.model.DbComment;
import com.discussion.portal.mongodb.model.DbQuestion;

public interface PortalDao {

	public String insertQuestion(DbQuestion question);
	public DbQuestion getQuestionById(String question);
	public List<String> getQuestionIdsByUserId(String userId);
	
	public String addAnswer(DbAnswer answer);
	
	public DbAnswer getAnswerById(String answerId);
	
	public List<DbAnswer> getAnswerByUserId(String userId);
	
	public List<DbAnswer> getFeeds();
	
	public String updateDbAnswer(DbAnswer dbAnswer);
	
	public String addComments(DbComment dbComment);
	
	public DbComment getCommentById(String commentId);
	
	public String updateDbComment(DbComment dbComment);
}
