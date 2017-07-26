package com.discussion.portal.helper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.discussion.portal.common.Constants.StatusCode;
import com.discussion.portal.dao.UserAuthDao;
import com.discussion.portal.dao.impl.DiscussionPortalDao;
import com.discussion.portal.dao.impl.DiscussionUserAuthDao;
import com.discussion.portal.helper.PortalHelper;
import com.discussion.portal.model.Question;
import com.discussion.portal.mongodb.model.DbQuestion;
import com.discussion.portal.utils.UserUtils;

/**
 * 
 * @author Vishal
 *
 */
@Component
public class DiscussionPortalHelper implements PortalHelper {

	@Autowired
	private DiscussionPortalDao portalDao;
	
	@Autowired 
	private UserUtils userUtils;

	@Autowired
	private DiscussionUserAuthDao userAuthDao;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String addQuestion(Question question) {
		DbQuestion dbQuestion = convertToDbQuestion(question);

		String status = portalDao.insertQuestion(dbQuestion);
		
		if(status == StatusCode.SUCCESS) {
			return userAuthDao.updateUserQuestion(dbQuestion.getQuestionId(), userUtils.getCurrentUser());
		}
		return "Some error occured while saving the question.";
	}

	@Override
	public Question viewQuestion(String questionId) {
		DbQuestion dbQuestion = portalDao.findQuestion(questionId);
		return convertToQuestion(dbQuestion);
	}

	private DbQuestion convertToDbQuestion(Question question) {

		DbQuestion dbQuestion = DbQuestion.builder().owner(userUtils.getCurrentUser())
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
