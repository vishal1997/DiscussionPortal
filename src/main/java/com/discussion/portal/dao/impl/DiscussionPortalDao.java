package com.discussion.portal.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.discussion.portal.common.Constants.MongoDbSignature;
import com.discussion.portal.common.Constants.StatusCode;
import com.discussion.portal.dao.PortalDao;
import com.discussion.portal.helper.impl.DiscussionPortalHelper;
import com.discussion.portal.model.Answer;
import com.discussion.portal.mongodb.model.DbAnswer;
import com.discussion.portal.mongodb.model.DbQuestion;
import com.discussion.portal.mongodb.model.DbUser;
import com.discussion.portal.mongodb.repository.AnswerRepository;
import com.discussion.portal.mongodb.repository.QuestionsRepository;
import com.discussion.portal.mongodb.repository.UserRepository;
import com.discussion.portal.utils.Json;
import com.mongodb.DuplicateKeyException;

@Component
public class DiscussionPortalDao implements PortalDao {

	@Autowired
	private QuestionsRepository quesRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AnswerRepository ansRepo;
	
	@Autowired
	private DiscussionUserAuthDao userDao;
	
	static Logger log = LoggerFactory.getLogger(DiscussionPortalDao.class);
	
	@Override
	public String insertQuestion(DbQuestion question) {

		try {
			log.info("\nTrying to insert Question with question details:\n" + Json.toJson(question));
			quesRepo.insert(question);
			
		} catch (Exception e) {
			log.error("\nException Occured\n" + Json.toJson(e));
			if(e.getCause().getMessage().contains(MongoDbSignature.DUPLICATE_CODE)) {
				return StatusCode.DUPLICATE;
			}
			throw new RuntimeException("Some error occured while saving the question", e);
		}
		return StatusCode.SUCCESS;
		
	}

	@Override
	public DbQuestion getQuestionById(String questionId) {
		String _questionId = questionId.toLowerCase();
		try {
			return quesRepo.findOne(_questionId);
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
			log.info("\nAnswer successfully added to DB\n" + Json.toJson(answer));
			return StatusCode.SUCCESS;
			
		} catch (Exception e) {
			log.error("\nUnable to add answer to DB\n" + Json.toJson(e));
			return "{\"status\":\"fail\"}";
		}
	}
	
	public String addAnswerToQuestionMap(Answer answer) {
		
		try {
			log.info("\nTrying to find Question by QuestionId " + answer.getAnswerId());
			DbQuestion question = quesRepo.findOne(answer.getQuestionId());
			question.addAnswerMap(answer);
			log.info("\nTrying to update question with new answer\n" + Json.toJson(question));
			quesRepo.save(question);
			return StatusCode.SUCCESS;
			
		} catch (Exception e) {
			log.error("Exception occured" + Json.toJson(e));
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

	@Override
	public List<Answer> getAnswerByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
