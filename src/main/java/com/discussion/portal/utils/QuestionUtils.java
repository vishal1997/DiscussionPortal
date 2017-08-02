package com.discussion.portal.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.discussion.portal.answer.response.model.AnswerResponse;
import com.discussion.portal.answer.response.model.QuestionResponse;
import com.discussion.portal.dao.impl.DiscussionPortalDao;
import com.discussion.portal.model.Answer;
import com.discussion.portal.model.Question;
import com.discussion.portal.mongodb.model.DbAnswer;
import com.discussion.portal.mongodb.model.DbQuestion;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Component
public class QuestionUtils {
	
	@Autowired
	private DiscussionPortalDao portalDao;
	
	static Logger log = LoggerFactory.getLogger(QuestionUtils.class);
	
	public DbQuestion convertToDbQuestion(Question question, String userId) {

		DbQuestion dbQuestion = DbQuestion.builder().owner(userId)
				.questionId(question.getQuestion().replace(" ", "-").replace("?", "").toLowerCase()).answersMap(new HashMap<String, String>())
				.creationDate(new Date()).question(question.getQuestion()).year(question.getYear())
				.build();
		
		log.info("\nConverted Question to dbQuestion as: \n" + Json.toJson(dbQuestion));

		return dbQuestion;
	}
	
	public Question convertToQuestion(DbQuestion dbQuestion) {

		Question question = new Question();
		
		if (dbQuestion == null) {
			return question;
		}

		question.setAnswersMap(dbQuestion.getAnswersMap());
		question.setCreationDate(dbQuestion.getCreationDate());
		question.setOwner(dbQuestion.getOwner());
		question.setTags(dbQuestion.getTags());
		question.setYear(dbQuestion.getYear());
		question.setQuestion(dbQuestion.getQuestion());
		question.setQuestionId(dbQuestion.getQuestionId());
		return question;
	}
	
	public QuestionResponse convertToQuestionResponse(DbQuestion dbQuestion) {
		
		if (dbQuestion == null) {
			return null;
		}

		QuestionResponse questionResponse = QuestionResponse.builder()
			.creationDate(dbQuestion.getCreationDate())
			.owner(dbQuestion.getOwner())
			.question(dbQuestion.getQuestion())
			.questionId(dbQuestion.getQuestionId())
			.tags(dbQuestion.getTags())
			.year(dbQuestion.getYear())
			.answerResponse(buildAnswerResponse(dbQuestion.getAnswersMap()))
			.build();
		
		log.info("\nConverted dbQuestion to QuestionResponse:\n" + Json.toJson(questionResponse));
		
		return questionResponse;
	}

	private List<AnswerResponse> buildAnswerResponse(Map<String, String> answerMap) {
		
		AnswerResponse answerResponse;
		List<AnswerResponse> answers = new ArrayList<AnswerResponse>();
		
		for(String key : answerMap.keySet()) {
			DbAnswer answer = portalDao.getAnswerById(answerMap.get(key));
			answerResponse = new AnswerResponse();
			answerResponse.setAnswer(answer.getAnswer());
			answerResponse.setAnswerBy(key);
			answerResponse.setAnswerId(answerMap.get(key));
			answerResponse.setDate(answer.getDate());
			
			answers.add(answerResponse);
		}
		
		log.info("\nThe Answer Repsonse generated is: \n" + Json.toJson(answers));
		
		return answers;
	}

}
