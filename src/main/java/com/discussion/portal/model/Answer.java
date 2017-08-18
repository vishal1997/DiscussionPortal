package com.discussion.portal.model;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Vishal
 *
 */
@Getter
@Setter
@Component
public class Answer {
	
	private String answerId;
	private String questionId;
	private String answer;
	private String question;
	private Date date;
	private String answeredBy;
	private Set<String> agree;
	private Set<String> disagree;
	private List<Comment>comments;
	private int noOfAgree;
	private int noOfDisagree;
	
}
