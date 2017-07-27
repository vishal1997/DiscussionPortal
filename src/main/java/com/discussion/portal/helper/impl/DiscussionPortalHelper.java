package com.discussion.portal.helper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.discussion.portal.common.Constants.StatusCode;
import com.discussion.portal.dao.impl.DiscussionPortalDao;
import com.discussion.portal.dao.impl.DiscussionUserAuthDao;
import com.discussion.portal.helper.PortalHelper;
import com.discussion.portal.model.Question;
import com.discussion.portal.mongodb.model.DbQuestion;
import com.discussion.portal.utils.QuestionUtils;

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
	private DiscussionUserAuthDao userAuthDao;
	
	@Autowired
	private QuestionUtils questionUtils;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String addQuestion(Question question, String userId) {
		DbQuestion dbQuestion = questionUtils.convertToDbQuestion(question, userId);

		String status = portalDao.insertQuestion(dbQuestion);
		
		if(status == StatusCode.SUCCESS) {
			return userAuthDao.updateUserQuestion(dbQuestion.getQuestionId(), userId);
		}
		return "Some error occured while saving the question.";
	}

	@Override
	public Question viewQuestion(String questionId) {
		DbQuestion dbQuestion = portalDao.findQuestion(questionId);
		return questionUtils.convertToQuestion(dbQuestion);
	}	

	@Override
	public List<Question> getQuestionsByUserId(String userId) {
		
		List<String> questionIds = portalDao.getQuestionIdsByUserId(userId);
		List<Question> questions = new ArrayList<Question>();
		
		questionIds.stream().forEach((questionId)->questions.add(viewQuestion(questionId)));
		
		return questions;
	}
}
