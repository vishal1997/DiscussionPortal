package com.discussion.portal.utils;

import org.springframework.stereotype.Component;

import com.discussion.portal.model.Question;
import com.discussion.portal.mongodb.model.DbQuestion;

@Component
public class QuestionUtils {
	
	public DbQuestion convertToDbQuestion(Question question, String userId) {

		DbQuestion dbQuestion = DbQuestion.builder().owner(userId)
				.questionId(question.getQuestion().replace(" ", "-")).answers(null)
				.creationDate(question.getCreationDate()).question(question.getQuestion()).year(question.getYear())
				.build();

		return dbQuestion;
	}
	
	public Question convertToQuestion(DbQuestion dbQuestion) {

		Question question = new Question();
		
		if (dbQuestion == null) {
			return question;
		}

		question.setAnswers(dbQuestion.getAnswers());
		question.setCreationDate(dbQuestion.getCreationDate());
		question.setOwner(dbQuestion.getOwner());
		question.setTags(dbQuestion.getTags());
		question.setYear(dbQuestion.getYear());
		question.setQuestion(dbQuestion.getQuestion());
		question.setQuestionId(dbQuestion.getQuestionId());
		return question;
	}

}
