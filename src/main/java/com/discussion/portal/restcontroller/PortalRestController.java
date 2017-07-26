package com.discussion.portal.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.discussion.portal.manager.impl.DiscussionPortalManager;
import com.discussion.portal.model.Answer;
import com.discussion.portal.model.Question;

@RestController
@EnableWebMvc
@RequestMapping("/api/v1/")
public class PortalRestController {

	@Autowired
	private DiscussionPortalManager portalManager;
	
	/**
	 * 
	 * @param question
	 * @return
	 */
	@RequestMapping(value="question", method=RequestMethod.PUT) 
	public String addQuestion(Question question) {
		
		try {
			return portalManager.addQuestion(question);
		} catch (Exception e) {
			throw e;
		}
		
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
