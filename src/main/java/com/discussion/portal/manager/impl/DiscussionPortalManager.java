package com.discussion.portal.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.discussion.portal.helper.impl.DiscussionPortalHelper;
import com.discussion.portal.manager.PortalManager;
import com.discussion.portal.model.Question;

/**
 * 
 * @author Vishal
 *
 */
@Component
public class DiscussionPortalManager implements PortalManager {

	@Autowired
	private DiscussionPortalHelper portalHelper;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String addQuestion(Question question) {
		return portalHelper.addQuestion(question);
	}
	
	@Override
	public Question viewQuestion(String questionId) {
		return portalHelper.viewQuestion(questionId);
	}

}
