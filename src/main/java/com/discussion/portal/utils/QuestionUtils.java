package com.discussion.portal.utils;

import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.discussion.portal.model.Question;
import com.discussion.portal.mongodb.model.DbQuestion;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Component
public class QuestionUtils {
	
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

}
