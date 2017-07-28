package com.discussion.portal.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Builder;
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
	
}
