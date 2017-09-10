package com.discussion.portal.manager;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import com.discussion.portal.model.Answer;
import com.discussion.portal.model.Comment;
import com.discussion.portal.model.Question;
import com.discussion.portal.model.User;
import com.discussion.portal.user.response.UserResponse;

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
	
	public List<Answer> getAnswerByUserId (String userId, int pageNo);
	
	public List<Answer> getFeeds(int pageNo);
	
	public String addUserOpinion(String userId, String opinion, String answerId);
	
	public String addComments(String answerId, String comment);
	
	public String addCommentOpinion(String commentId, String opinion);
	
	public String deleteAnswer(String answerId, String userId);
	
	public String deleteComment(String commentId, String userId);
	
	public List<Comment> getCommentsByAnswerId(String answerId);
	
	public String registerUser(User user);
	
	public UserResponse getUserProfileDetails(String username);
	
	public Map<String, String> userNameIdPair();
	
	public String resetPassword(String userId, String password);
}
