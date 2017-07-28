package com.discussion.portal.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.discussion.portal.dao.impl.DiscussionPortalDao;
import com.discussion.portal.model.Answer;
import com.discussion.portal.mongodb.model.DbAnswer;

@Component
public class AnswerUtils {

	@Autowired
	private DiscussionPortalDao portalDao;
	
	
	public DbAnswer convertAnswerToDbAnswer(Answer answer) {
		DbAnswer dbAnswer = DbAnswer.builder()
				.answer(answer.getAnswer())
				.answerId(answer.getAnswerId())
				.date(answer.getDate())
				.questionId(answer.getQuestionId())
				.userId(answer.getAnsweredBy())
				.build();
				
		return dbAnswer;
	}
	
	public Answer convertDbAnswerToAnswer(DbAnswer dbAnswer) {

		Answer answer = new Answer();
		
		if(dbAnswer == null) {
			return answer;
		}
		
		answer.setAnswer(dbAnswer.getAnswer());
		answer.setAnsweredBy(dbAnswer.getUserId());
		answer.setDate(dbAnswer.getDate());
		answer.setAnswerId(dbAnswer.getAnswerId());
		answer.setQuestionId(dbAnswer.getQuestionId());
		answer.setQuestion(portalDao.getQuestionById(dbAnswer.getQuestionId()).getQuestion());
		return answer;
	}
}
