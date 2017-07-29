package com.discussion.portal.restcontroller;

import java.util.Date;
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

import com.discussion.portal.dao.impl.DiscussionUserAuthDao;
import com.discussion.portal.manager.impl.DiscussionPortalManager;
import com.discussion.portal.model.Answer;
import com.discussion.portal.model.Question;
import com.discussion.portal.model.User;
import com.discussion.portal.mongodb.model.DbUser;
import com.discussion.portal.utils.Json;
import com.discussion.portal.utils.QuestionUtils;
import com.discussion.portal.utils.UserUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

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
	public Map<String, String> getUser() {
		
		Map <String, String> userIdMap = new HashMap<String, String>();
		userIdMap.put("userId", userUtils.getCurrentUser());
		return userIdMap;
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
	public Question getQuestionById(@PathVariable("questionId") final String questionId) throws Exception {
		try {
			return portalManager.getQuestionById(questionId);
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
	/*
	@RequestMapping(value="{username}/answer/{answerId}", method = RequestMethod.GET)
	public Answer viewAnswer(@PathVariable("username") final String username, 
							 @PathVariable("answerId") final String answerId) {
		
		return new Answer();
	}
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
	
	@RequestMapping(value = "/myanswers", method = RequestMethod.GET) 
	public List<Answer> getAnswerByUserId() {
		return portalManager.getAnswerByUserId(userUtils.getCurrentUser());
	}
	
	@RequestMapping(value = "/{userId}/answers", method = RequestMethod.GET) 
	public List<Answer> getAnswerByOtherUserId(@PathVariable("userId") final String userId) {
		return portalManager.getAnswerByUserId(userId);
	}
	
}
