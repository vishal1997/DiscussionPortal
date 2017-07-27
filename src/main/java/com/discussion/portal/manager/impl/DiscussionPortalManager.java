package com.discussion.portal.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.discussion.portal.common.Constants.StatusCode;
import com.discussion.portal.helper.impl.DiscussionPortalHelper;
import com.discussion.portal.manager.PortalManager;
import com.discussion.portal.model.Question;
import com.discussion.portal.mongodb.model.DbQuestion;

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
	public String addQuestion(Question question, String userId) {
		DbQuestion dbQuestion = portalHelper.covertToDbQuestion(question, userId);
		String status = portalHelper.addQuestion(dbQuestion, userId);
		if(status == StatusCode.SUCCESS) {
			return portalHelper.addUserQuestion(dbQuestion.getQuestionId(), userId);
		}
		return "Some error occured while saving the question.";
	}
	
	@Override
	public Question viewQuestion(String questionId) {
		return portalHelper.viewQuestion(questionId);
	}

	@Override
	public List<Question> getQuestionsByUserId(String userId) {
		return portalHelper.getQuestionsByUserId(userId);
	}

}
