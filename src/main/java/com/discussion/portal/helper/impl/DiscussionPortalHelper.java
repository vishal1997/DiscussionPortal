package com.discussion.portal.helper.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.discussion.portal.dao.impl.DiscussionPortalDao;
import com.discussion.portal.dao.impl.DiscussionUserAuthDao;
import com.discussion.portal.helper.PortalHelper;
import com.discussion.portal.model.Answer;
import com.discussion.portal.model.Question;
import com.discussion.portal.mongodb.model.DbQuestion;
import com.discussion.portal.mongodb.model.DbUser;
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
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String addQuestion(DbQuestion dbQuestion, String userId) {
		return portalDao.insertQuestion(dbQuestion);
	}

	@Override
	public Question viewQuestion(String questionId) {
		DbQuestion dbQuestion = portalDao.findQuestion(questionId);
		return questionUtils.convertToQuestion(dbQuestion);
	}	

	@Override
	public List<Question> getQuestionsByUserId(String userId) {
		
		List<String> questionIds = portalDao.getQuestionIdsByUserId(userId);
		List<Question> questions = new ArrayList<Question>();
		
		questionIds.stream().forEach((questionId)->questions.add(viewQuestion(questionId)));
		
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
	public String addAnswer(Answer answer) {
		return portalDao.addAnswer(answer);
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
		
		return answerObj;
		
	}
	
	public String addUserToSession(String userId, HttpSession session) {
		DbUser dbUser = userAuthDao.getUserByUserId(userId);
		session.setAttribute("user", userUtils.convertDbUserToUser(dbUser));
		session.setAttribute("dbUser", dbUser);
		return "Successfully added user to session with attribute user";
	}
}
