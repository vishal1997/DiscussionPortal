package com.discussion.portal.model;

import java.util.Date;

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
	private Date date;
	private String answeredBy;
	
}
