package com.discussion.portal.helper.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.discussion.portal.dao.impl.DiscussionPortalDao;
import com.discussion.portal.dao.impl.DiscussionUserAuthDao;
import com.discussion.portal.helper.PortalHelper;
import com.discussion.portal.manager.impl.DiscussionPortalManager;
import com.discussion.portal.model.Answer;
import com.discussion.portal.model.Question;
import com.discussion.portal.model.User;
import com.discussion.portal.mongodb.model.DbAnswer;
import com.discussion.portal.mongodb.model.DbQuestion;
import com.discussion.portal.mongodb.model.DbUser;
import com.discussion.portal.utils.AnswerUtils;
import com.discussion.portal.utils.Json;
import com.discussion.portal.utils.QuestionUtils;
import com.discussion.portal.utils.UserUtils;

/**
 * 
 * @author Vishal
 *
 */
@Component
public class DiscussionPortalHelper implements PortalHelper {

	@Autowired
	private DiscussionPortalDao portalDao;

	@Autowired
	private DiscussionUserAuthDao userAuthDao;
	
	@Autowired
	private QuestionUtils questionUtils;
	
	@Autowired
	private UserUtils userUtils;
	
	@Autowired
	private AnswerUtils answerUtils;
	
	/**
	 * {@inheritDoc}
	 */
	static Logger log = LoggerFactory.getLogger(DiscussionPortalHelper.class);
	
	@Override
	public String addQuestion(DbQuestion dbQuestion, String userId) {
		return portalDao.insertQuestion(dbQuestion);
	}

	@Override
	public Question getQuestionById(String questionId) {
		DbQuestion dbQuestion = portalDao.getQuestionById(questionId);
		return questionUtils.convertToQuestion(dbQuestion);
	}	

	@Override
	public List<Question> getQuestionsByUserId(String userId) {
		
		List<String> questionIds = portalDao.getQuestionIdsByUserId(userId);
		List<Question> questions = new ArrayList<Question>();
		
		questionIds.stream().forEach((questionId)->questions.add(getQuestionById(questionId)));
		
		return questions;
	}
	
	@Override
	public DbQuestion covertToDbQuestion(Question question, String userId) {
		return questionUtils.convertToDbQuestion(question, userId);
	}
	
	@Override
	public String addUserQuestion(String questionId, String userId) {
		return userAuthDao.updateUserQuestion(questionId, userId);
	}

	@Override
	public String addAnswer(DbAnswer answer) {
		return portalDao.addAnswer(answer);
	}

	public String addAnswerToQuestionMap(Answer answer) {
		return portalDao.addAnswerToQuestionMap(answer);
	}
	
	public String addAnswerToMap(Answer answer) {
		return userAuthDao.addAnswerToMap(answer);
	}
	
	public Answer generateAnswer(String userId, String answer, String questionId) {
		
		Answer answerObj = new Answer();
		
		answerObj.setAnswer(answer);
		answerObj.setAnsweredBy(userId);
		answerObj.setAnswerId(userId + "'s-answer-to-" + questionId);
		answerObj.setQuestionId(questionId);
		answerObj.setDate(new Date());
		
		log.info("\nThe answer object generated:\n" + Json.toJson(answerObj));
		
		return answerObj;
		
	}
	
	public DbAnswer convertToDbAnswer(Answer answer) {
		return answerUtils.convertAnswerToDbAnswer(answer);
	}
	
	public String addUserToSession(String userId, HttpSession session) {
		DbUser dbUser = getUserByUserId(userId);
		session.setAttribute("user", convertDbUserToUser(dbUser));
		session.setAttribute("dbUser", dbUser);
		return "Successfully added user to session with attribute user";
	}

	@Override
	public Answer getAnswerById(String answerId) {
		
		DbAnswer dbAnswer = portalDao.getAnswerById(answerId);
		return answerUtils.convertDbAnswerToAnswer(dbAnswer);
	}
	
	public List<Answer> getAnswerByUserId(String userId) {
		Map<String, String> questionAnswerMap = getUserByUserId(userId).getUserAnswerMap();
		return getAnswersByMap(questionAnswerMap);
		
	}
	
	public List<Answer> getAnswersByMap(Map<String,String> questionAnswerMap) {
		List<Answer> answers = new ArrayList<Answer>();
		for(String key : questionAnswerMap.keySet()) {
			answers.add(getAnswerById(questionAnswerMap.get(key)));
		}
		return answers;
	}
	
	public DbUser getUserByUserId(String userId) {
		return userAuthDao.getUserByUserId(userId);
	}
	
	public User convertDbUserToUser(DbUser dbUser) {
		return userUtils.convertDbUserToUser(dbUser);
	}
}
