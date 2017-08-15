package com.discussion.portal.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.discussion.portal.dao.impl.DiscussionPortalDao;
import com.discussion.portal.model.Answer;
import com.discussion.portal.mongodb.model.DbAnswer;

@Component
public class AnswerUtils {

	@Autowired
	private DiscussionPortalDao portalDao;
	
	@Autowired
	private UserUtils userUtils;

	static Logger log = LoggerFactory.getLogger(AnswerUtils.class);
	
	public DbAnswer convertAnswerToDbAnswer(Answer answer) {
		DbAnswer dbAnswer = DbAnswer.builder()
				.answer(answer.getAnswer())
				.answerId(answer.getAnswerId())
				.date(answer.getDate())
				.questionId(answer.getQuestionId())
				.userId(answer.getAnsweredBy())
				.agree(answer.getAgree())
				.disAgree(answer.getDisAgree())
				.build();
		log.info("\nDB Answer generated:\n" + Json.toJson(dbAnswer));
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
		
		if(dbAnswer.getAgree().isEmpty()) {
			System.out.println("If");
			answer.setUserAgree(false);
		} else if(dbAnswer.getAgree().contains(userUtils.getCurrentUser())) {
			System.out.println("else if");
			answer.setUserAgree(true);
		} else {
			System.out.println("esle");
			answer.setUserAgree(false);
		}
		
		//answer.setUserDisAgree(dbAnswer.getDisAgree().contains(userUtils.getCurrentUser()));
		
		if(dbAnswer.getAgree() != null) {
			answer.setAgree(dbAnswer.getAgree());
		}
		
		if(dbAnswer.getDisAgree() != null) {
			answer.setDisAgree(dbAnswer.getDisAgree());
		}
		
		answer.setNoOfAgree(dbAnswer.getNoOfAgree());
		answer.setNoOfDisAgree(dbAnswer.getNoOfDisagree());
		log.info("\nAnswer Generated:\n" + Json.toJson(answer));
		return answer;
	}
}
