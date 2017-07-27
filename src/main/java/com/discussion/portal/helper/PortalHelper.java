package com.discussion.portal.helper;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.discussion.portal.model.Answer;
import com.discussion.portal.model.Question;
import com.discussion.portal.mongodb.model.DbQuestion;
import com.discussion.portal.mongodb.model.DbUser;

/**
 * 
 * @author Vishal
 *
 */
public interface PortalHelper {
	
	/**
	 * 
	 * @param question
	 * @return
	 */
	public String addQuestion(DbQuestion question, String userId);
	
	public Question viewQuestion(String question);
	
	public List<Question> getQuestionsByUserId(String userId);
	
	public DbQuestion covertToDbQuestion(Question question, String userId);
	
	public String addUserQuestion(String questionId, String userId);
	
	public String addAnswer(Answer answer);
	
	public String addUserToSession(String userId, HttpSession session);
	
}
