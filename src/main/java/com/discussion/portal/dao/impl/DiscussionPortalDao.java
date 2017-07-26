package com.discussion.portal.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.discussion.portal.dao.PortalDao;
import com.discussion.portal.mongodb.model.DbQuestion;
import com.discussion.portal.mongodb.repository.QuestionsRepository;

@Component
public class DiscussionPortalDao implements PortalDao {

	@Autowired
	private QuestionsRepository quesRepo;
	
	@Override
	public String insertQuestion(DbQuestion question) {

		try {
			quesRepo.insert(question);
		} catch (Exception e) {
			throw e;
		}
		return "{\"status\":\"success\"}";
		
	}

	@Override
	public DbQuestion findQuestion(String questionId) {
		try {
			return quesRepo.findOne(questionId);
		} catch (Exception e) {
			throw new RuntimeException("Error while finding the question", e);
		}
		
	}

}
