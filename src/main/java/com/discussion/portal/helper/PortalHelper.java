package com.discussion.portal.helper;

import java.util.List;

import com.discussion.portal.model.Question;
import com.discussion.portal.mongodb.model.DbQuestion;

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
}
