package com.discussion.portal.helper.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.discussion.portal.answer.response.model.QuestionResponse;
import com.discussion.portal.common.Constants.Opinion;
import com.discussion.portal.common.Constants.StatusCode;
import com.discussion.portal.dao.impl.DiscussionPortalDao;
import com.discussion.portal.dao.impl.DiscussionUserAuthDao;
import com.discussion.portal.helper.PortalHelper;
import com.discussion.portal.model.Answer;
import com.discussion.portal.model.Comment;
import com.discussion.portal.model.Question;
import com.discussion.portal.model.User;
import com.discussion.portal.mongodb.model.DbAnswer;
import com.discussion.portal.mongodb.model.DbComment;
import com.discussion.portal.mongodb.model.DbQuestion;
import com.discussion.portal.mongodb.model.DbUser;
import com.discussion.portal.utils.AnswerUtils;
import com.discussion.portal.utils.Json;
import com.discussion.portal.utils.QuestionUtils;
import com.discussion.portal.utils.UserUtils;
import com.discussion.portal.utils.CommentUtils;

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
	
	@Autowired
	private UserUtils userUtils;
	
	@Autowired
	private AnswerUtils answerUtils;
	
	@Autowired
	private CommentUtils commentUtils;
	
	/**
	 * {@inheritDoc}
	 */
	static Logger log = LoggerFactory.getLogger(DiscussionPortalHelper.class);
	
	@Override
	public String addQuestion(DbQuestion dbQuestion, String userId) {
		return portalDao.insertQuestion(dbQuestion);
	}

	/**
	 * @deprecated
	 */
	@Override
	public Question getQuestionById(String questionId) {
		DbQuestion dbQuestion = portalDao.getQuestionById(questionId);
		return questionUtils.convertToQuestion(dbQuestion);
	}	
	
	public QuestionResponse getAllAnswers(String questionId) {
		DbQuestion dbQuestion = portalDao.getQuestionById(questionId);
		return questionUtils.convertToQuestionResponse(dbQuestion);
	}	

	@Override
	public List<Question> getQuestionsByUserId(String userId) {
		
		List<String> questionIds = portalDao.getQuestionIdsByUserId(userId);
		List<Question> questions = new ArrayList<Question>();
		
		questionIds.stream().forEach((questionId)->questions.add(getQuestionById(questionId)));
		
		return questions;
	}
	
	@Override
	public DbQuestion covertToDbQuestion(Question question, String userId) {
		return questionUtils.convertToDbQuestion(question, userId);
	}
	
	@Override
	public String addUserQuestion(String questionId, String userId) {
		return userAuthDao.updateUserQuestion(questionId, userId);
	}

	@Override
	public String addAnswer(DbAnswer answer) {
		return portalDao.addAnswer(answer);
	}

	public String addAnswerToQuestionMap(Answer answer) {
		return portalDao.addAnswerToQuestionMap(answer);
	}
	
	public String addAnswerToMap(Answer answer) {
		return userAuthDao.addAnswerToMap(answer);
	}
	
	public Answer generateAnswer(String userId, String answer, String questionId) {
		
		Answer answerObj = new Answer();
		
		String _userId = userId.toLowerCase();
		
		answerObj.setAnswer(answer);
		answerObj.setAnsweredBy(userId);
		answerObj.setAnswerId(_userId + "'s-answer-to-" + questionId);
		answerObj.setQuestionId(questionId);
		answerObj.setDate(new Date());
		answerObj.setNoOfAgree(0);
		answerObj.setNoOfDisagree(0);
		log.info("\nThe answer object generated:\n" + Json.toJson(answerObj));
		
		return answerObj;
		
	}
	
	public DbAnswer convertToDbAnswer(Answer answer) {
		return answerUtils.convertAnswerToDbAnswer(answer);
	}
	
	public String addUserToSession(String userId, HttpSession session) {
		DbUser dbUser = getUserByUserId(userId);
		session.setAttribute("user", convertDbUserToUser(dbUser));
		session.setAttribute("dbUser", dbUser);
		return "Successfully added user to session with attribute user";
	}

	@Override
	public Answer getAnswerById(String answerId) {
		DbAnswer dbAnswer = portalDao.getAnswerById(answerId);
		return answerUtils.convertDbAnswerToAnswer(dbAnswer);
	}
	
	/**
	 * @deprecated
	 */
	public List<Answer> getAnswerByUserId(String userId) {
		Map<String, String> questionAnswerMap = getUserByUserId(userId).getUserAnswerMap();
		log.info("\nThe Answer map for the user:" + userId + "\n" + Json.toJson(userId));
		return getAnswersByMap(questionAnswerMap);
	}

	public List<Answer> getAnswersByUserId(String userId) {
		
		List<DbAnswer> dbAnswers = portalDao.getAnswerByUserId(userId);
		List<Answer> answers = new ArrayList<Answer>();
		for(DbAnswer dbAnswer : dbAnswers ) {
			answers.add(answerUtils.convertDbAnswerToAnswer(dbAnswer));
		}
		return answers;
	}
	
	public List<Answer> getAnswersByMap(Map<String,String> questionAnswerMap) {
		List<Answer> answers = new ArrayList<Answer>();
		log.info("\nStarted fetching answers from answer Map");
		questionAnswerMap.keySet().stream().forEach((key)->answers.add(getAnswerById(questionAnswerMap.get(key))));
		log.info("\nFetched answers \n" + Json.toJson(answers));
		return answers;
	}
	
	@Override
	public DbUser getUserByUserId(String userId) {
		return userAuthDao.getUserByUserId(userId);
	}
	
	public User convertDbUserToUser(DbUser dbUser) {
		return userUtils.convertDbUserToUser(dbUser);
	}

	@Override
	public List<Answer> getFeeds() {
		
		List<DbAnswer> dbAnswers=portalDao.getFeeds();
		List<Answer> answers = new ArrayList<Answer>();
		for(DbAnswer dbAnswer : dbAnswers ) {
		answers.add(answerUtils.convertDbAnswerToAnswer(dbAnswer));
		}
		return answers;
	}

	@Override
	public String addUserOpinion(String userId, String opinion, String answerId) {
		
		DbAnswer dbAnswer = portalDao.getAnswerById(answerId);
		
		if(opinion.equalsIgnoreCase(Opinion.AGREE)) {
			dbAnswer.updateAgree(userId);
		} else if(opinion.equalsIgnoreCase(Opinion.DISAGREE)) {
			dbAnswer.updateDisagree(userId);
		}
		return portalDao.updateDbAnswer(dbAnswer);
	}

	@Override
	public String addComments(Comment commentObj) {
		
		DbComment dbComment = commentUtils.convertCommentToDbComment(commentObj);
		return portalDao.addComments(dbComment);
	}

	@Override
	public Comment generateComment(String comment, String answerId) {
		
		Comment commentObj = new  Comment();
		
		commentObj.setComment(comment);
		commentObj.setCommentId(userUtils.getCurrentUser() + answerId + (new Date()).toString().replace(" ", "-").replace(":","-"));
		commentObj.setDate(new Date());
		commentObj.setUserId(userUtils.getCurrentUser());
		commentObj.setNoOfAgree(0);
		commentObj.setNoOfDisagree(0);
		commentObj.setAnswerId(answerId);
		return commentObj;
	}

	@Override
	public String addCommentIdToDbAnswer(String commentId, String answerId) {
		
		DbAnswer dbAnswer = portalDao.getAnswerById(answerId);
		dbAnswer.addCommentId(commentId);
		return portalDao.updateDbAnswer(dbAnswer);
	}

	@Override
	public String addCommentOpinion(String commentId, String opinion) {
		DbComment dbComment = portalDao.getCommentById(commentId);
		
		if(opinion.equalsIgnoreCase(Opinion.AGREE)) {
			dbComment.updateAgree(userUtils.getCurrentUser());
		} else if(opinion.equalsIgnoreCase(Opinion.DISAGREE)) {
			dbComment.updateDisagree(userUtils.getCurrentUser());
		}
		
		return portalDao.updateDbComment(dbComment);
	}

	@Override
	public String deleteAnswer(String answerId, String userId) {
	    
		DbAnswer dbAnswer = portalDao.getAnswerById(answerId);
		if(userId.equalsIgnoreCase(dbAnswer.getUserId())) {
			return portalDao.deleteAnswer(dbAnswer);
		}
		
		return StatusCode.ERROR;
	}
	
	public String getAnswerIdByCommentId(String commentId) {
		
		DbComment dbComment = portalDao.getCommentById(commentId);
		return dbComment.getAnswerId();
	}

	@Override
	public String deleteComment(String commentId, String userId) {
		
		DbComment dbComment = portalDao.getCommentById(commentId);
		if(userId.equalsIgnoreCase(dbComment.getUserId())) {
			return portalDao.deleteComment(dbComment);
		}
		return StatusCode.ERROR;
	
	}

	@Override
	public String deleteCommentIdFromDbAnswer(String answerId, String commentId) {
		
		DbAnswer dbAnswer = portalDao.getAnswerById(answerId);
		dbAnswer.removeCommentId(commentId);
		return portalDao.updateDbAnswer(dbAnswer);
	}

	@Override
	public String deleteAnswerIdFromUser(String answerId, DbUser dbUser) {
		
		dbUser.removeAnswerFromMap(answerId);
		return portalDao.updateDbUser(dbUser);
	}

	@Override
	public List<DbComment> getCommentByAnswerId(String answerId) {
		
		DbAnswer dbAnswer = portalDao.getAnswerById(answerId);
		List<String> commentIds = dbAnswer.getCommentId();
		List<DbComment> dbComment =new ArrayList<DbComment>();
		
		for(String commentId:commentIds) {
			dbComment.add(portalDao.getCommentById(commentId));
		}
		return dbComment;
	}

	@Override
	public List<Comment> convertDbCommentToComment(List<DbComment> dbComments) {
		
		List<Comment> comment = new ArrayList<Comment>();
		for(DbComment dbComment : dbComments) {
			comment.add(commentUtils.convertDbCommentToComment(dbComment));
		}
		return comment;
	}

	@Override
	public String registerUser(User user) {
		
		DbUser dbUser = convertUserToDbUser(user);
		return userAuthDao.registerUser(dbUser);
	}

	@Override
	public DbUser convertUserToDbUser(User user) {
		
		DbUser dbUser = new DbUser();
		dbUser.setUsername(user.getUsername());
		dbUser.setPassword(user.getPassword());
		return dbUser;
	}
}
