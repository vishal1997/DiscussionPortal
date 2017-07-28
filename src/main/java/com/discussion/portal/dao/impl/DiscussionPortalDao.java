package com.discussion.portal.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.discussion.portal.common.Constants.StatusCode;
import com.discussion.portal.dao.PortalDao;
import com.discussion.portal.model.Answer;
import com.discussion.portal.mongodb.model.DbAnswer;
import com.discussion.portal.mongodb.model.DbQuestion;
import com.discussion.portal.mongodb.model.DbUser;
import com.discussion.portal.mongodb.repository.AnswerRepository;
import com.discussion.portal.mongodb.repository.QuestionsRepository;
import com.discussion.portal.mongodb.repository.UserRepository;

@Component
public class DiscussionPortalDao implements PortalDao {

	@Autowired
	private QuestionsRepository quesRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AnswerRepository ansRepo;
	
	@Override
	public String insertQuestion(DbQuestion question) {

		try {
			quesRepo.insert(question);
			
		} catch (Exception e) {
			throw e;
		}
		return StatusCode.SUCCESS;
		
	}

	@Override
	public DbQuestion getQuestionById(String questionId) {
		try {
			return quesRepo.findOne(questionId);
		} catch (Exception e) {
			throw new RuntimeException("Error while finding the question", e);
		}
		
	}
	
	@Override
	public List<String> getQuestionIdsByUserId(String userId) {
		DbUser user = userRepo.findOne(userId);
		try {
			return user.getQuestionId();
		} catch (NullPointerException e) {
			throw new RuntimeException("User cannot be null Null", e);
		}
	}

	@Override
	public String addAnswer(DbAnswer answer) {
		
		try {
			ansRepo.insert(answer);
			return StatusCode.SUCCESS;
			
		} catch (Exception e) {
			return "{\"status\":\"fail\"}";
		}
	}
	
	public String addAnswerToQuestionMap(Answer answer) {
		
		try {
			
			DbQuestion question = quesRepo.findOne(answer.getQuestionId());
			question.addAnswerMap(answer);
			quesRepo.save(question);
			return StatusCode.SUCCESS;
			
		} catch (Exception e) {
			return "{\"status\":\"fail\"}";
		}
	}

	@Override
	public DbAnswer getAnswerById(String answerId) {
		
		try {
			return ansRepo.findOne(answerId);
		} catch (Exception e) {
			throw new RuntimeException("Error while finding the answer", e);
		}
	}
	
}
