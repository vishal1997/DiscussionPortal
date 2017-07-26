package com.discussion.portal.manager;

import java.io.UnsupportedEncodingException;

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
	public String addQuestion(Question question);
	
	/**
	 * 
	 * @param question
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public Question viewQuestion(String questionId) throws UnsupportedEncodingException;
	
}
