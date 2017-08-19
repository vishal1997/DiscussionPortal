package com.discussion.portal.manager;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.discussion.portal.model.Answer;
import com.discussion.portal.model.Question;

/**
 * 
 * @author Vishal
 *
 */
public interface PortalManager {

	/**
	 * 
	 * @param question
	 * @return
	 */
	public String addQuestion(Question question, String userId);
	
	/**
	 * 
	 * @param question
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public Question getQuestionById(String questionId) throws UnsupportedEncodingException;
	
	
	public List<Question> getQuestionsByUserId(String userId);
	
	
	public String addAnswer(String questionId, String userId, String answer);
	
	public Answer getAnswerById(String answerId);
	
	public List<Answer> getAnswerByUserId (String userId);
	
	public List<Answer> getFeeds();
	
	public String addUserOpinion(String userId, String opinion, String answerId);
	
	public String addComments(String answerId, String comment);
	
	public String addCommentOpinion(String commentId, String opinion);
}
