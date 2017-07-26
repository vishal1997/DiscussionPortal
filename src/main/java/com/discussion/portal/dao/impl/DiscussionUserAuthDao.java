package com.discussion.portal.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.discussion.portal.dao.UserAuthDao;
import com.discussion.portal.mongodb.model.DbUser;
import com.discussion.portal.mongodb.repository.UserRepository;
import com.discussion.portal.utils.UserUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DiscussionUserAuthDao implements UserAuthDao{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private UserUtils userUtils;
	
	@Override
	public String createUser() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		DbUser currentUser = userRepository.findOne(userUtils.getCurrentUser());
		
		System.out.print(mapper.writeValueAsString(currentUser));
		
		if(currentUser!=null) {
			return "Already Present";
		}

		DbUser user = new DbUser();
		user.setUsername(userUtils.getCurrentUser());
		user.setPassword(1212);
		
		userRepository.insert(user);
		
		return "Successfully Created User";
	}

	@Override
	public String updateUserQuestion(String questionId, String userId) {
		
		DbUser user = userRepository.findOne(userId);
		
		System.out.println(user.getUsername());
		
		user.addQuestionId(questionId);
		userRepository.save(user);
		
		return "Successfully added question";
	}

}
