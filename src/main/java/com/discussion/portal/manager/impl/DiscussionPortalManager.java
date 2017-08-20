package com.discussion.portal.manager.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.discussion.portal.answer.response.model.QuestionResponse;
import com.discussion.portal.common.Constants.StatusCode;
import com.discussion.portal.helper.impl.DiscussionPortalHelper;
import com.discussion.portal.manager.PortalManager;
import com.discussion.portal.model.Answer;
import com.discussion.portal.model.Comment;
import com.discussion.portal.model.Question;
import com.discussion.portal.mongodb.model.DbAnswer;
import com.discussion.portal.mongodb.model.DbQuestion;

/**
 * 
 * @author Vishal
 *
 */
@Component
public class DiscussionPortalManager implements PortalManager {

	@Autowired
	private DiscussionPortalHelper portalHelper;
	
	static Logger log = LoggerFactory.getLogger(DiscussionPortalManager.class);
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String addQuestion(Question question, String userId) {
		
		DbQuestion dbQuestion = portalHelper.covertToDbQuestion(question, userId);
		String status = portalHelper.addQuestion(dbQuestion, userId);
		if(status == StatusCode.SUCCESS) {
			return portalHelper.addUserQuestion(dbQuestion.getQuestionId(), userId);
		}
		if(StringUtils.equals(status, StatusCode.DUPLICATE)) {
			String smallQuestion = question.getQuestion();
			if(smallQuestion.length() > 20) {
				smallQuestion = question.getQuestion().substring(0, 20);
			}
			return "The question " + smallQuestion + "... already exists.";
		}
		return "Some error occured";
	}
	
	/**
	 * @deprecated 
	 * Use getAllAnswers
	 * 
	 */
	@Override
	public Question getQuestionById(String questionId) {
		return portalHelper.getQuestionById(questionId);
	}
	
	public QuestionResponse getAllAnswers(String questionId) {
		return portalHelper.getAllAnswers(questionId);
	}	

	@Override
	public List<Question> getQuestionsByUserId(String userId) {
		return portalHelper.getQuestionsByUserId(userId);
	}

	@Override
	public String addAnswer(String questionId, String userId, String answer) {
		
		Answer answerObj = portalHelper.generateAnswer(userId, answer, questionId);
		
		String status = portalHelper.addAnswerToMap(answerObj);
		
		if(StringUtils.equals(status, StatusCode.SUCCESS)) {
			if(StringUtils.equals(portalHelper.addAnswerToQuestionMap(answerObj), StatusCode.SUCCESS)) {
				
				log.info("Adding Answer to Question Map successfull");
				
				DbAnswer dbAnswer = portalHelper.convertToDbAnswer(answerObj);
				status = portalHelper.addAnswer(dbAnswer);
			}
		}
		if(status == StatusCode.SUCCESS) {
			return "Answer Posted";
		} else {
			return "You have already answered the question";
		}
		
	}

	public String addUserToSession(String userId, HttpSession session) {
		return portalHelper.addUserToSession(userId, session);
	}

	@Override
	public Answer getAnswerById(String answerId) {
		
		return portalHelper.getAnswerById(answerId);
	}

	@Override
	public List<Answer> getAnswerByUserId(String userId) {
		return portalHelper.getAnswersByUserId(userId);
	}

	@Override
	public List<Answer> getFeeds() {
		
		return portalHelper.getFeeds();
	}

	@Override
	public String addUserOpinion(String userId, String opinion, String answerId) {
		
		return portalHelper.addUserOpinion(userId, opinion, answerId);
	}

	@Override
	public String addComments(String answerId, String comment) {
		
		Comment commentObj = portalHelper.generateComment(comment, answerId);
		String status = portalHelper.addComments(commentObj);
		
		if(status.equals(StatusCode.SUCCESS)) {
			portalHelper.addCommentIdToDbAnswer(commentObj.getCommentId(), answerId);
			return "Comment Posted";
		}
		return StatusCode.ERROR;
	}

	@Override
	public String addCommentOpinion(String commentId, String opinion) {
		return portalHelper.addCommentOpinion(commentId, opinion);
	}

	@Override
	public String deleteAnswer(String answerId, String userId) {
		return portalHelper.deleteAnswer(answerId, userId);
	}

	@Override
	public String deleteComment(String commentId, String userId) {
        return portalHelper.deleteComment(commentId, userId);
		
	}
}
