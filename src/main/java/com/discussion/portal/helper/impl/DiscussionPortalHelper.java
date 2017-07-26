package com.discussion.portal.helper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.discussion.portal.dao.impl.DiscussionPortalDao;
import com.discussion.portal.helper.PortalHelper;
import com.discussion.portal.model.Question;
import com.discussion.portal.mongodb.model.DbQuestion;

/**
 * 
 * @author Vishal
 *
 */
@Component
public class DiscussionPortalHelper implements PortalHelper {

	@Autowired
	private DiscussionPortalDao portalDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String addQuestion(Question question) {
		DbQuestion ques = convertToDbQuestion(question);
		return portalDao.insertQuestion(ques);
	}

	@Override
	public Question viewQuestion(String questionId) {
		DbQuestion dbQuestion = portalDao.findQuestion(questionId);
		return convertToQuestion(dbQuestion);
	}

	private DbQuestion convertToDbQuestion(Question question) {

		DbQuestion dbQuestion = DbQuestion.builder().owner(question.getOwner())
				.questionId(question.getQuestion().replace(" ", "-")).answers(null)
				.creationDate(question.getCreationDate()).question(question.getQuestion()).year(question.getYear())
				.build();

		return dbQuestion;
	}

	private Question convertToQuestion(DbQuestion dbQuestion) {

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
