package com.discussion.portal.manager;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.discussion.portal.model.Question;

/**
 * 
 * @author Vishal
 *
 */
public interface PortalManager {

	/**
	 * 
	 * @param question
	 * @return
	 */
	public String addQuestion(Question question, String userId);
	
	/**
	 * 
	 * @param question
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public Question viewQuestion(String questionId) throws UnsupportedEncodingException;
	
	
	public List<Question> getQuestionsByUserId(String userId);
}
