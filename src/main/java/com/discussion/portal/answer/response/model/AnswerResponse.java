package com.discussion.portal.answer.response.model;

import java.util.Date;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Vishal
 *
 */
@Getter
@Setter
public class AnswerResponse {
	
	private String answerId;
	private String userId;
	private String name;
	private Date date;
	private String answer;
	private Set<String> agree;
	private Set<String> disagree;
	private int noOfAgree;
	private int noOfDisagree;
}
