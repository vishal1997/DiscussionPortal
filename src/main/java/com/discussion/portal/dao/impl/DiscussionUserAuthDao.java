package com.discussion.portal.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.discussion.portal.common.Constants.StatusCode;
import com.discussion.portal.dao.UserAuthDao;
import com.discussion.portal.model.Answer;
import com.discussion.portal.mongodb.model.DbUser;
import com.discussion.portal.mongodb.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DiscussionUserAuthDao implements UserAuthDao{

	@Autowired
	private UserRepository userRepository;
	
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
		
		System.out.println(user.getUsername());
		
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
		
		if(userAnswerMap.containsKey(answer.getQuestionId())) {
			return StatusCode.DUPLICATE;
		}
		
		user.addAnswerToMap(answer.getQuestionId(), answer.getAnswerId());
		userRepository.save(user);
		
		return StatusCode.SUCCESS;
	}

}
