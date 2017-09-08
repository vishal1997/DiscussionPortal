package com.discussion.portal.helper;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.discussion.portal.model.Answer;
import com.discussion.portal.model.Comment;
import com.discussion.portal.model.Question;
import com.discussion.portal.model.User;
import com.discussion.portal.mongodb.model.DbAnswer;
import com.discussion.portal.mongodb.model.DbComment;
import com.discussion.portal.mongodb.model.DbQuestion;
import com.discussion.portal.mongodb.model.DbUser;
import com.discussion.portal.user.response.UserResponse;

/**
 * 
 * @author Vishal
 *
 */
public interface PortalHelper {
	
	/**
	 * 
	 * @param question
	 * @return
	 */
	public String addQuestion(DbQuestion question, String userId);
	
	public Question getQuestionById(String questionId);
	
	public List<Question> getQuestionsByUserId(String userId);
	
	public DbQuestion covertToDbQuestion(Question question, String userId);
	
	public String addUserQuestion(String questionId, String userId);
	
	public String addAnswer(DbAnswer answer);
	
	public String addUserToSession(String userId, HttpSession session);
	
	public Answer getAnswerById(String answerId);
	
	public List<Answer> getAnswerByUserId(String questionAnswerMap);

	public DbUser getUserByUserId(String userId);
	
	public List<Answer> getFeeds();
	
	public String addUserOpinion(String userId, String opinion, String answerId);
	
	public String addComments(Comment commentObj);
	
	public Comment generateComment(String answerId, String comment);
	
	public String addCommentIdToDbAnswer(String commentId, String answerId);
	
	public String addCommentOpinion(String commentId, String opinion);
	
	public String deleteAnswer(String answerId, String userId);
	
	public String deleteComment(String commentId, String userId);
	
	public String deleteCommentIdFromDbAnswer(String answerId, String commentId);
	
	public List<DbComment> getCommentByAnswerId(String answerId);
	
	public List<Comment> convertDbCommentToComment(List<DbComment> dbComments);
	
	public String registerUser(User user);
	
	public boolean userAlreadyPresent(String userId);
	
	public UserResponse getUserProfileDetails(DbUser dbUser);
	
	public Map<String, String> userNameIdPair();
}
