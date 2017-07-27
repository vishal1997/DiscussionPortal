package com.discussion.portal.restcontroller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.discussion.portal.dao.impl.DiscussionUserAuthDao;
import com.discussion.portal.manager.impl.DiscussionPortalManager;
import com.discussion.portal.model.Answer;
import com.discussion.portal.model.Question;
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
	
	@RequestMapping(value="question", method=RequestMethod.GET) 
	public String addQuestion(Question ques) {
		
		Question question = new Question();
		question.setAnswers(null);
		question.setCreationDate(new Date());
		question.setQuestion("question answer 13");
		
		try {
			return portalManager.addQuestion(question,userUtils.getCurrentUser());
		} catch (Exception e) {
			throw e;
		}	
	}
	
	
	@RequestMapping(value="/questions", method = RequestMethod.GET)
	public List<Question> getQuestionsByUserId() {
		
		return portalManager.getQuestionsByUserId(userUtils.getCurrentUser());
	}
	
	
	/**
	 * 
	 * @param questionId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="answers/{questionId}", method = RequestMethod.GET) 
	public Question viewQuestion(@PathVariable("questionId") final String questionId) throws Exception {
		try {
			return portalManager.viewQuestion(questionId);
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
	@RequestMapping(value="{username}/answer/{answerId}", method = RequestMethod.GET)
	public Answer viewAnswer(@PathVariable("username") final String username, 
							 @PathVariable("answerId") final String answerId) {
		
		return new Answer();
	}
}
