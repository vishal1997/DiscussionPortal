package com.discussion.portal.helper.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.discussion.portal.answer.response.model.QuestionResponse;
import com.discussion.portal.common.Constants.Opinion;
import com.discussion.portal.common.Constants.StatusCode;
import com.discussion.portal.dao.impl.DiscussionPortalDao;
import com.discussion.portal.dao.impl.DiscussionUserAuthDao;
import com.discussion.portal.helper.PortalHelper;
import com.discussion.portal.iter.ConnectIter;
import com.discussion.portal.iter.StudentInfo;
import com.discussion.portal.mail.Mail;
import com.discussion.portal.mail.SendMail;
import com.discussion.portal.model.Answer;
import com.discussion.portal.model.Comment;
import com.discussion.portal.mongodb.model.PasswordResetToken;
import com.discussion.portal.model.Question;
import com.discussion.portal.model.User;
import com.discussion.portal.mongodb.model.DbAnswer;
import com.discussion.portal.mongodb.model.DbComment;
import com.discussion.portal.mongodb.model.DbOtp;
import com.discussion.portal.mongodb.model.DbQuestion;
import com.discussion.portal.mongodb.model.DbUser;
import com.discussion.portal.user.response.UserResponse;
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
	
	@Autowired
	private ConnectIter iter;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SendMail sendMail;
	
	@Autowired
	private Mail mail;
	
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
		DbUser dbUser = getUserByUserId(dbAnswer.getUserId());
		return answerUtils.convertDbAnswerToAnswer(dbAnswer, dbUser);
	}
	
	/**
	 * @deprecated
	 */
	public List<Answer> getAnswerByUserId(String userId) {
		Map<String, String> questionAnswerMap = getUserByUserId(userId).getUserAnswerMap();
		log.info("\nThe Answer map for the user:" + userId + "\n" + Json.toJson(userId));
		return getAnswersByMap(questionAnswerMap);
	}

	@Override
	public List<Answer> getAnswersByUserId(String userId, int pageNo) {
		
		List<DbAnswer> dbAnswers = portalDao.getAnswerByUserId(userId, pageNo);
		List<Answer> answers = new ArrayList<Answer>();
		DbUser dbUser = getUserByUserId(userId);
		for(DbAnswer dbAnswer : dbAnswers ) {
			answers.add(answerUtils.convertDbAnswerToAnswer(dbAnswer,dbUser));
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
	public List<Answer> getFeeds(int pageNo) {
		
		Page<DbAnswer> dbAnswers=portalDao.getFeeds(pageNo);
		List<Answer> answers = new ArrayList<Answer>();
		for(DbAnswer dbAnswer : dbAnswers ) {
			DbUser dbUser = getUserByUserId(dbAnswer.getUserId());
			answers.add(answerUtils.convertDbAnswerToAnswer(dbAnswer, dbUser));
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
		List<DbComment>dbComments = getCommentByAnswerId(dbAnswer.getAnswerId());
		if(userId.equalsIgnoreCase(dbAnswer.getUserId())) {
			
			portalDao.deleteAnswer(dbAnswer);
			userAuthDao.deleteAnswerToMap(dbAnswer.getQuestionId(), userId);
			portalDao.deleteAnswerToMap(dbAnswer.getQuestionId(), userId);
			
			if(dbComments!=null) {
				for(DbComment dbComment : dbComments) {
					portalDao.deleteComment(dbComment);
				}
			}
			return StatusCode.SUCCESS;
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
	public List<DbComment> getCommentByAnswerId(String answerId) {
		
		DbAnswer dbAnswer = portalDao.getAnswerById(answerId);
		List<String> commentIds = dbAnswer.getCommentId();
		List<DbComment> dbComment =new ArrayList<DbComment>();
		
		if(commentIds!=null) {
			for(String commentId:commentIds) {
				dbComment.add(portalDao.getCommentById(commentId));
			}
		}
		return dbComment;
	}

	@Override
	public List<Comment> convertDbCommentToComment(List<DbComment> dbComments) {
		
		List<Comment> comment = new ArrayList<Comment>();
		for(DbComment dbComment : dbComments) {
			
			DbUser dbUser = getUserByUserId(dbComment.getUserId());
			comment.add(commentUtils.convertDbCommentToComment(dbComment, dbUser.getName()));
		}
		return comment;
	}

	@Override
	public String registerUser(User user) {
		
		String studentDetails = iter.connect(user.getUsername(), user.getPassword());
		
		log.info("\nUser details=" + studentDetails);
		
		if(studentDetails == StatusCode.INVALID) {
			return StatusCode.INVALID;
		}
		
		StudentInfo studentInfo = (StudentInfo) Json.fromJson(studentDetails,StudentInfo.class);
		DbUser dbUser = userUtils.convertStudentInfoToDbUser(studentInfo, user);
		return userAuthDao.registerUser(dbUser);
	}

	@Override
	public boolean userAlreadyPresent(String userId) {
		DbUser dbUser = userAuthDao.getUserByUserId(userId);
		if(dbUser==null) {
			return false;
		}
		return true;
	}

	@Override
	public UserResponse getUserProfileDetails(DbUser dbUser) {
		return userUtils.convertDbUserToUserDetails(dbUser);
	}

	@Override
	public Map<String, String> userNameIdPair() {
		
		DbUser dbUser = getUserByUserId(userUtils.getCurrentUser());
		Map<String , String> nameIdPair = new HashMap<String,String>();
		nameIdPair.put("user_id", dbUser.getUsername());
		nameIdPair.put("name", dbUser.getName());
		return nameIdPair;
	}

	@Override
	public String resetpassword(String userId, String password) {
		String encyptPassword = passwordEncoder.encode(password);
		log.info("\nNew password encrypted"+encyptPassword);
		return userAuthDao.resetPassword(userId, encyptPassword);
	}
	
	@Override
	public String resetEmailId(String userId, String emailId) {
		return userAuthDao.resetEmailId(userId, emailId);
	}

	@Override
	public List<Question> getAllQuestions(int pageNo) {
		
		Page<DbQuestion> dbQuestion = portalDao.getAllQuestions(pageNo);
		List<Question> question = new ArrayList<Question>();
		for(DbQuestion dbquestion : dbQuestion) {
			question.add(questionUtils.convertToQuestion(dbquestion));
		}
		return question;
	}
	
	@Override
	public List<User> search(String name) {
		
		List<DbUser> dbUser = userAuthDao.getUserByName(name);
		List<User> user = new ArrayList<User>();
		for(DbUser dbuser : dbUser) {
			user.add(userUtils.convertDbUserToUserSearch(dbuser));
		}
		return user;
	}

	@Override
	public String generateResetToken(String userId) {
		
		DbUser dbUser = getUserByUserId(userId);
		if(dbUser == null) {
			return StatusCode.ERROR;
		}
		PasswordResetToken token = new PasswordResetToken();
		token.setToken(UUID.randomUUID().toString());
		token.setUser(dbUser);
		token.setExpiryDate(30);
		portalDao.saveResetToken(token);
		return dbUser.getEmailId();
	}
	
	@Override 
	public String sendMail(String emailId, String subject, String message) {
		mail.setEmailId(emailId);
		mail.setMessage(message);
		mail.setSubject(subject);
		sendMail.sendMail();
		return StatusCode.SUCCESS;
	}

	@Override
	public Map<String, String> generateOtp(String userId) {
		
		Map<String, String> user = new HashMap<String, String>();
		DbUser dbUser = getUserByUserId(userId);
		if(dbUser == null) {
			user.put("status", StatusCode.ERROR);
			return user;
		}
		String otp = String.format("%04d", new Random().nextInt(10000));
		user.put("emailId",dbUser.getEmailId());
		user.put("otp", otp);
		DbOtp dbOtp = getOtpObject(userId, otp);
		userAuthDao.saveOtp(dbOtp);
		return user;
	}
	
	private DbOtp getOtpObject(String userId, String otp) {
		
		DbOtp dbOtp = new DbOtp();
		dbOtp.setUserId(userId);
		dbOtp.setOtp(otp);
		return dbOtp;
	}

	@Override
	public String verifyOtp(String userId, String otp) {
		
		DbOtp dbOtp = userAuthDao.getOtpByUserId(userId);
		if(!dbOtp.getOtp().equals(otp)) {
			return StatusCode.ERROR;
		}
		return StatusCode.SUCCESS;
	}
}
