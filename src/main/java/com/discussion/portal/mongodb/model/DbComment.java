package com.discussion.portal.mongodb.model;


import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Vishal
 *
 */
@Builder
@Getter
@Setter
@Document(collection="comments")
public class DbComment extends AgreeDisagree{
	
	@Id
	private String commentId;
	private String answerId;
	private String userId;
	private String comment;
	private Date date;
}
