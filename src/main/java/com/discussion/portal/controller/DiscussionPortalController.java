package com.discussion.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author Vishal
 *
 */
@Controller
public class DiscussionPortalController {
	
	/**
	 * 
	 * @param question
	 * @return
	 */
	@RequestMapping(value="/question", method = RequestMethod.GET)
	public ModelAndView addQuestion( @ModelAttribute("question") String question) {
		ModelAndView model1 = new ModelAndView("QuestionAdded");
		model1.addObject("msg", "Hi");
		return model1;
	}
}
