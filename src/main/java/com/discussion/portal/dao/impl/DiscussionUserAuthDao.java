package com.discussion.portal.dao.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.discussion.portal.common.Constants.StatusCode;
import com.discussion.portal.dao.UserAuthDao;
import com.discussion.portal.model.Answer;
import com.discussion.portal.mongodb.model.DbUser;
import com.discussion.portal.mongodb.repository.UserRepository;
import com.discussion.portal.utils.Json;
import com.fasterxml.jackson.core.JsonProcessingException;


/**
 * 
 * @author Vishal
 *
 */
@Component
public class DiscussionUserAuthDao implements UserAuthDao{

	@Autowired
	private UserRepository userRepository;
	static Logger log = LoggerFactory.getLogger(DiscussionUserAuthDao.class);
	
	@Override
	public String createUser(String userId) throws JsonProcessingException {

		DbUser currentUser = userRepository.findOne(userId);
		
		if(currentUser!=null) {
			return "Already Present";
		}

		DbUser user = new DbUser();
		user.setUsername(userId);
		user.setPassword("1212");
		
		userRepository.insert(user);
		
		return "Successfully Created User";
	}

	@Override
	public String updateUserQuestion(String questionId, String userId) {
		
		DbUser user = getUserByUserId(userId);
		
		user.addQuestionId(questionId);
		userRepository.save(user);
		
		return "Successfully added question";
	}

	public DbUser getUserByUserId(String userId) {
		
		return userRepository.findOne(userId);
	}
	
	public String addAnswerToMap(Answer answer) {
		
		DbUser user = getUserByUserId(answer.getAnsweredBy());
		Map<String, String> userAnswerMap = user.getUserAnswerMap();
		if(userAnswerMap != null) {
			if(userAnswerMap.containsKey(answer.getQuestionId())) {
				log.error("\nUser has already answered this question\n" + Json.toJson(answer));
				return StatusCode.DUPLICATE;
			}
		}
		
		user.addAnswerToMap(answer.getQuestionId(), answer.getAnswerId());
		log.info("\nTrying to Update user Database with answerId:" + answer.getAnswerId());
		userRepository.save(user);
		
		return StatusCode.SUCCESS;
	}

	@Override
	public String registerUser(DbUser dbUser) {
		
		try {
			userRepository.insert(dbUser);
			return StatusCode.SUCCESS;
		} catch (Exception er) {
			return StatusCode.ERROR;
		}
	}

	@Override
	public String deleteAnswerToMap(String questionId, String userId) {
		
		try {
			DbUser dbUser= getUserByUserId(userId);
			dbUser.deleteAnswerToMap(questionId);
			userRepository.save(dbUser);
			log.info("\nAnswer Map deleted from userRepo"+ Json.toJson(dbUser));
			return StatusCode.SUCCESS;
		} catch(Exception e) {
			return StatusCode.ERROR;
		}
	}

	@Override
	public String resetPassword(String userId, String password) {
		
	   try {
			DbUser dbUser = getUserByUserId(userId);
			dbUser.setPassword(password);
			userRepository.save(dbUser);
			log.info("\nPassword changed"+ Json.toJson(dbUser));
			return StatusCode.SUCCESS;
	   } catch (Exception e) {
		   log.error("\nError while changing password, UserId:" + userId );
		   return StatusCode.FAILED;
	   }
	}
	
	@Override
	public String resetEmailId(String userId, String emailId) {
		
		try {
			DbUser dbUser = getUserByUserId(userId);
			dbUser.setEmailId(emailId);
			userRepository.save(dbUser);
			log.info("\nEmailId Changed"+ Json.toJson(dbUser));
			return StatusCode.SUCCESS;
		} catch(Exception e) {
			log.error("\nError while changing emailId, userId="+ userId);
			return StatusCode.FAILED;
		}
	}
}
