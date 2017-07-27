package com.discussion.portal.manager.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.discussion.portal.common.Constants.StatusCode;
import com.discussion.portal.dao.PortalDao;
import com.discussion.portal.helper.impl.DiscussionPortalHelper;
import com.discussion.portal.manager.PortalManager;
import com.discussion.portal.model.Answer;
import com.discussion.portal.model.Question;
import com.discussion.portal.mongodb.model.DbQuestion;
import com.discussion.portal.mongodb.model.DbUser;

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

	@Override
	public String addAnswer(String questionId, String userId, String answer) {
		
		Answer answerObj = portalHelper.generateAnswer(userId, answer, questionId);
		
		String status = portalHelper.addAnswerToMap(answerObj);
		
		if(StringUtils.equals(status, StatusCode.SUCCESS)) {
			return portalHelper.addAnswer(answerObj);
		}
		
		return status;
	}

	public String addUserToSession(String userId, HttpSession session) {
		return portalHelper.addUserToSession(userId, session);
	}
}
