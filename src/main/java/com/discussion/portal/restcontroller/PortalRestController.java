package com.discussion.portal.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.discussion.portal.answer.response.model.QuestionResponse;
import com.discussion.portal.common.Constants.StatusCode;
import com.discussion.portal.dao.impl.DiscussionUserAuthDao;
import com.discussion.portal.manager.impl.DiscussionPortalManager;
import com.discussion.portal.model.Answer;
import com.discussion.portal.model.Comment;
import com.discussion.portal.model.Question;
import com.discussion.portal.model.User;
import com.discussion.portal.user.response.UserResponse;
import com.discussion.portal.utils.Json;
import com.discussion.portal.utils.UserUtils;
import com.fasterxml.jackson.core.JsonProcessingException;


/**
 * 
 * @author Vishal
 *
 */
@RestController
@EnableWebMvc
@RequestMapping("/api/v1/")
public class PortalRestController {

	@Autowired
	private DiscussionPortalManager portalManager;
	
	@Autowired
	private UserUtils userUtils;
	
	@Autowired
	private DiscussionUserAuthDao authDao;
	
	
	static Logger log = LoggerFactory.getLogger(PortalRestController.class);
	
	/**
	 * 
	 * @param question
	 * @return
	 */
	
	@RequestMapping("/user")
	public String getUser() {
		return userUtils.getCurrentUser();
	}
	

	@RequestMapping("/create")
	public Map<String, String> createUser() throws JsonProcessingException {
		
		Map <String, String> auth = new HashMap<String, String>();
		auth.put("status", authDao.createUser(userUtils.getCurrentUser()));
		return auth;
	}
	
	@RequestMapping(value="question", method=RequestMethod.POST) 
	public Map<String, String> addQuestion(@RequestBody Question question) {
		
		try {
			log.info("\nSuccessfully entered addQuestion() with question \n" + Json.toJson(question));
			
			Map<String, String> questionMap = new HashMap<String, String>();
			questionMap.put("status", portalManager.addQuestion(question,userUtils.getCurrentUser()));
			
			log.info("\nSending response of add question: \n" + Json.toJson(questionMap));
			
			return questionMap;
		} catch (Exception e) {
			log.error("\nSome error occured while adding question\n" + Json.toJson(e) );
			return null;
		}	
	}
	
	
	@RequestMapping(value="/questions", method = RequestMethod.GET)
	public List<Question> getQuestionsByUserId() {
		
		return portalManager.getQuestionsByUserId(userUtils.getCurrentUser());
	}
	
	
	@RequestMapping(value="/{userId}/questions", method = RequestMethod.GET)
	public List<Question> getQuestionsByOtherUserId(@PathVariable("userId") final String userId) {
		
		return portalManager.getQuestionsByUserId(userId);
	}
	
	/**
	 * 
	 * @param questionId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="answers/{questionId}", method = RequestMethod.GET) 
	public QuestionResponse getQuestionById(@PathVariable("questionId") final String questionId) throws Exception {
		try {
			QuestionResponse response = portalManager.getAllAnswers(questionId);
			log.info("Response Being sent is:\n" + Json.toJson(response));
			return response;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	 * @param username
	 * @param answerId
	 * @return
	 */
	
	@RequestMapping(value="/userSession", method = RequestMethod.GET)
	public String addUserToSession(HttpSession session) {
		return portalManager.addUserToSession(userUtils.getCurrentUser(), session);
	}
	
	@RequestMapping(value="/userTest", method = RequestMethod.GET)
	public User getUser1(HttpSession session) {
		
		return (User) session.getAttribute("user");
	}
	
	@RequestMapping(value="/{questionId}/answer", method = RequestMethod.POST) 
	public Map<String, String> addAnswer(@PathVariable("questionId") final String questionId, @RequestBody String answer) {
		log.info("\nSuccessfully entered addAnswer() with answer: " + answer + "and questionId: " + questionId);
		
		Map<String, String> answerMap = new HashMap<String, String>();
		answerMap.put("status",portalManager.addAnswer(questionId, userUtils.getCurrentUser(), answer));
		log.info("\nThe response is:\n" + Json.toJson(answerMap));
		return answerMap;
	}
	
	@RequestMapping(value = "/answer/{answerId}", method = RequestMethod.GET)
	public Answer getAnswerById(@PathVariable("answerId") final String answerId) {
		return portalManager.getAnswerById(answerId);
	}
	
	@RequestMapping(value = "/myanswers", method = RequestMethod.POST) 
	public List<Answer> getAnswerByUserId(@RequestBody final String pageno) {
		int pageNo = Integer.parseInt(pageno);
		List<Answer> answers = portalManager.getAnswerByUserId(userUtils.getCurrentUser(),pageNo);
		log.info("\nResponse for getAnswerByUserId\n" + Json.toJson(answers));
		return answers;
	}
	
	@RequestMapping(value = "/{userId}/answers", method = RequestMethod.POST) 
	public List<Answer> getAnswerByOtherUserId(@PathVariable("userId") final String userId,
												@RequestBody final String pageno) {
		int pageNo=Integer.parseInt(pageno);
		return portalManager.getAnswerByUserId(userId, pageNo);
	}
	
	@RequestMapping(value = "/me", method = RequestMethod.GET) 
	public Map<String, String> getNameIdPair() {
		return portalManager.userNameIdPair();
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.PUT)
	public List<Answer> getFeeds(@RequestBody String pageNo_) {
		int pageNo= Integer.parseInt(pageNo_);
		System.out.println(pageNo);
		return portalManager.getFeeds(pageNo);
	}
	
	@RequestMapping(value ="/{answerId}", method = RequestMethod.PUT) 
	public void addUserOpinion(@RequestBody final String opinion,
							   @PathVariable("answerId") final String answerId){
		
		portalManager.addUserOpinion(getUser(), opinion, answerId);
	}
	
	
	@RequestMapping(value = "/comments/{answerId}", method = RequestMethod.PUT)
	public Map<String, String> addComments(@RequestBody final Comment comment,
							  @PathVariable("answerId") final String answerId) {
		try {
			Map<String, String> commentStatus = new HashMap<String, String>();
			String status = portalManager.addComments(answerId, comment.getComment());
			commentStatus.put("status", status);
			return commentStatus;
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value = "/comments/opinion/{commentId}" , method = RequestMethod.PUT)
	public Map<String,String> addCommentOpinion(@RequestBody final String opinion,
									@PathVariable("commentId") final String commentId) {

		Map<String,String> status=new HashMap<String, String>();
		status.put("status", portalManager.addCommentOpinion(commentId, opinion));
		return status;
	}
	

	@RequestMapping(value = "/delete/answer/{answerId}", method = RequestMethod.GET)
	public Map<String, String> deleteAnswer(@PathVariable("answerId") final String answerId) {
		
		try {
			Map<String, String> deleteStatus = new HashMap<String, String>();
			String status = portalManager.deleteAnswer(answerId, userUtils.getCurrentUser());
			deleteStatus.put("status", status);
			return deleteStatus;
		} catch(Exception e) {
			throw e;
		}
	}
	
	@RequestMapping(value = "delete/comment/{commentId}",method = RequestMethod.GET)
	public Map<String, String> deleteComment(@PathVariable("commentId") final String commentId) {
		try {
			Map<String,String> deleteStatus = new HashMap<String, String>();
			String status = portalManager.deleteComment(commentId, userUtils.getCurrentUser());
			deleteStatus.put("status",  status);
			return deleteStatus;
		} catch(Exception e) {
			throw e;
		}
	}
	
	@RequestMapping(value = "allcomments/{answerId}",method = RequestMethod.GET)
	public List<Comment> getCommentByAnswerId(@PathVariable("answerId")final String answerId) {
		try {
			return portalManager.getCommentsByAnswerId(answerId);
		} catch (Exception e) {
			throw e;
		}
	}	
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public Map<String,String> registerUser(@RequestBody final User user) {
		
		String status = portalManager.registerUser(user);
		Map<String, String> registerStatus = new HashMap<String, String>();
		registerStatus.put("status", status);
		return registerStatus;
	}
	
	@RequestMapping(value="/userdetails", method = RequestMethod.GET) 
	public UserResponse getUserProfileDetails() {
		return portalManager.getUserProfileDetails(userUtils.getCurrentUser());
	}
	
	@RequestMapping(value="/{userId}/userprofile", method = RequestMethod.GET)
	public UserResponse getOtherUserProfileDetails(@PathVariable("userId") final String userId) {
		return portalManager.getUserProfileDetails(userId);
	}
	
	@RequestMapping(value="/resetpassword/", method = RequestMethod.PUT)
	public Map<String, String> resetPassword(@RequestBody final String password) {
		
		String status = portalManager.resetPassword(userUtils.getCurrentUser(),password);
		Map<String, String> statusMap = new HashMap<String, String>();
		statusMap.put("status", status);
		return statusMap;
	}
	
	@RequestMapping(value="/resetemail", method = RequestMethod.PUT) 
	public Map<String, String> resetEmailId(@RequestBody final String emailId) {
		
		String status = portalManager.resetEmailId(userUtils.getCurrentUser(), emailId);
		Map<String, String> resetStatus = new HashMap<String, String>();
		resetStatus.put("status", status);
		return resetStatus;
	}
}



