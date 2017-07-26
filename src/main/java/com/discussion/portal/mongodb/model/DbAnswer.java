package com.discussion.portal.mongodb.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;

/**
 * 
 * @author AkashKG
 *
 */
@Builder
@Getter
@Document(collection="answers")
public class DbAnswer {
	
	@Id
	private String answerId;
	
	private String userId;
	private String questionId;
	
	private String answer;
	private Date date;

}
