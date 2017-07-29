package com.discussion.portal.dao.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.discussion.portal.common.Constants.StatusCode;
import com.discussion.portal.dao.UserAuthDao;
import com.discussion.portal.helper.impl.DiscussionPortalHelper;
import com.discussion.portal.model.Answer;
import com.discussion.portal.mongodb.model.DbUser;
import com.discussion.portal.mongodb.repository.UserRepository;
import com.discussion.portal.utils.Json;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DiscussionUserAuthDao implements UserAuthDao{

	@Autowired
	private UserRepository userRepository;
	static Logger log = LoggerFactory.getLogger(DiscussionUserAuthDao.class);
	
	@Override
	public String createUser(String userId) throws JsonProcessingException {
/*		ObjectMapper mapper = new ObjectMapper();*/
		DbUser currentUser = userRepository.findOne(userId);
/*		
		System.out.print(mapper.writeValueAsString(currentUser));*/
		
		if(currentUser!=null) {
			return "Already Present";
		}

		DbUser user = new DbUser();
		user.setUsername(userId);
		user.setPassword(1212);
		
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
		
/*		List<DbUser> dbUsers = userRepository.findByUsername(userId);
		DbUser dbUser = null;
		if(dbUsers!= null && dbUsers.size()!=0) {
			dbUser = dbUsers.get(0);
		}
		
		return dbUser;*/
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

}
