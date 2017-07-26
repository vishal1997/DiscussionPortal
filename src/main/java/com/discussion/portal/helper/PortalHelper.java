package com.discussion.portal.helper;

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
	public String addQuestion(Question question);
	
	public Question viewQuestion(String question);
}
