package com.discussion.portal.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.discussion.portal.dao.impl.DiscussionPortalDao;
import com.discussion.portal.model.Answer;
import com.discussion.portal.mongodb.model.DbAnswer;
import com.discussion.portal.mongodb.model.DbUser;

/**
 * 
 * @author Vishal
 *
 */
@Component
public class AnswerUtils {

	@Autowired
	private DiscussionPortalDao portalDao;
	
	@Autowired
	private UserUtils userUtils;
	
	static Logger log = LoggerFactory.getLogger(AnswerUtils.class);
	
	public DbAnswer convertAnswerToDbAnswer(Answer answer) {

		DbAnswer dbAnswer = new DbAnswer();
		dbAnswer.setAnswer(answer.getAnswer());
		dbAnswer.setAnswerId(answer.getAnswerId());
		dbAnswer.setUserId(answer.getAnsweredBy());
		dbAnswer.setQuestionId(answer.getQuestionId());
		dbAnswer.setAgree(answer.getAgree());
		dbAnswer.setDisagree(answer.getDisagree());
		dbAnswer.setDate(answer.getDate());
		dbAnswer.setCommentId(answer.getCommentId());
		
		log.info("\nDB Answer generated:\n" + Json.toJson(dbAnswer));
		return dbAnswer;
	}
	
	public Answer convertDbAnswerToAnswer(DbAnswer dbAnswer, DbUser dbUser) {

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
		answer.setAnsweredByName(dbUser.getName());
		answer.setSec(dbUser.getSec());
		if(dbAnswer.getAgree()!=null) {
			answer.setAgreeStatus(dbAnswer.getAgree().contains(userUtils.getCurrentUser()));
		} else {
			answer.setAgreeStatus(false);
		}
		
		if(dbAnswer.getDisagree()!=null) {
			answer.setDisagreeStatus(dbAnswer.getDisagree().contains(userUtils.getCurrentUser()));
		} else {
			answer.setDisagreeStatus(false);
		}
		
		if(dbAnswer.getAgree() != null) {
			answer.setAgree(dbAnswer.getAgree());
		}
		
		if(dbAnswer.getDisagree() != null) {
			answer.setDisagree(dbAnswer.getDisagree());
		}
		
		answer.setNoOfAgree(dbAnswer.getNoOfAgree());
		answer.setNoOfDisagree(dbAnswer.getNoOfDisagree());
		answer.setNoOfComment(dbAnswer.getNoOfComment());
		log.info("\nAnswer Generated:\n" + Json.toJson(answer));
		return answer;
	}
}
