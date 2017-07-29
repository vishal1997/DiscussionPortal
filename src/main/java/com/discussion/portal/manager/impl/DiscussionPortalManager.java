package com.discussion.portal.manager.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.discussion.portal.common.Constants.StatusCode;
import com.discussion.portal.dao.PortalDao;
import com.discussion.portal.helper.impl.DiscussionPortalHelper;
import com.discussion.portal.manager.PortalManager;
import com.discussion.portal.model.Answer;
import com.discussion.portal.model.Question;
import com.discussion.portal.model.User;
import com.discussion.portal.mongodb.model.DbAnswer;
import com.discussion.portal.mongodb.model.DbQuestion;
import com.discussion.portal.mongodb.model.DbUser;
import com.discussion.portal.restcontroller.PortalRestController;

/**
 * 
 * @author Vishal
 *
 */
@Component
public class DiscussionPortalManager implements PortalManager {

	@Autowired
	private DiscussionPortalHelper portalHelper;
	
	static Logger log = LoggerFactory.getLogger(DiscussionPortalManager.class);
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String addQuestion(Question question, String userId) {
	
		log.info("Entered Discussion Portal manager class");
		
		DbQuestion dbQuestion = portalHelper.covertToDbQuestion(question, userId);
		String status = portalHelper.addQuestion(dbQuestion, userId);
		if(status == StatusCode.SUCCESS) {
			return portalHelper.addUserQuestion(dbQuestion.getQuestionId(), userId);
		}
		if(StringUtils.equals(status, StatusCode.DUPLICATE)) {
			String smallQuestion = question.getQuestion();
			if(smallQuestion.length() > 20) {
				smallQuestion = question.getQuestion().substring(0, 20);
			}
			return "The question " + smallQuestion + "... already exists.";
		}
		return "Some error occured";
	}
	
	@Override
	public Question getQuestionById(String questionId) {
		return portalHelper.getQuestionById(questionId);
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
			if(StringUtils.equals(portalHelper.addAnswerToQuestionMap(answerObj), StatusCode.SUCCESS)) {
				DbAnswer dbAnswer = portalHelper.convertToDbAnswer(answerObj);
				status = portalHelper.addAnswer(dbAnswer);
			}
		}
		
		return status;
	}

	public String addUserToSession(String userId, HttpSession session) {
		return portalHelper.addUserToSession(userId, session);
	}

	@Override
	public Answer getAnswerById(String answerId) {
		
		return portalHelper.getAnswerById(answerId);
	}

	@Override
	public List<Answer> getAnswerByUserId(String userId) {
		return portalHelper.getAnswerByUserId(userId);
	}
}
