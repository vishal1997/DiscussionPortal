package com.discussion.portal.utils;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.discussion.portal.model.Answer;
import com.discussion.portal.model.Question;
import com.discussion.portal.mongodb.model.DbQuestion;

@Component
public class QuestionUtils {
	
	public DbQuestion convertToDbQuestion(Question question, String userId) {

		DbQuestion dbQuestion = DbQuestion.builder().owner(userId)
				.questionId(question.getQuestion().replace(" ", "-")).answersMap(new HashMap<String, String>())
				.creationDate(question.getCreationDate()).question(question.getQuestion()).year(question.getYear())
				.build();

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
