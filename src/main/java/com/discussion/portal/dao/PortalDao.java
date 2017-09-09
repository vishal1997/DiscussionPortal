package com.discussion.portal.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.discussion.portal.mongodb.model.DbAnswer;
import com.discussion.portal.mongodb.model.DbComment;
import com.discussion.portal.mongodb.model.DbQuestion;
import com.discussion.portal.mongodb.model.DbUser;

/**
 * 
 * @author Vishal
 *
 */
public interface PortalDao {

	public String insertQuestion(DbQuestion question);
	public DbQuestion getQuestionById(String question);
	public List<String> getQuestionIdsByUserId(String userId);
	
	public String addAnswer(DbAnswer answer);
	
	public DbAnswer getAnswerById(String answerId);
	
	public List<DbAnswer> getAnswerByUserId(String userId);
	
	public Page<DbAnswer> getFeeds(int pageNo);
	
	public String updateDbAnswer(DbAnswer dbAnswer);
	
	public String addComments(DbComment dbComment);
	
	public DbComment getCommentById(String commentId);
	
	public String updateDbComment(DbComment dbComment);
	
	public String deleteAnswer(DbAnswer dbAnswer);
	
	public String deleteComment(DbComment commentId);
	
	public String updateDbUser(DbUser dbUser);
	
	public String deleteAnswerToMap(String questionId, String userId);
	
}
