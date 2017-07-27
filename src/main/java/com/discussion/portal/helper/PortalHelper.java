package com.discussion.portal.helper;

import java.util.List;

import com.discussion.portal.model.Question;

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
	public String addQuestion(Question question, String userId);
	
	public Question viewQuestion(String question);
	
	public List<Question> getQuestionsByUserId(String userId);
}
