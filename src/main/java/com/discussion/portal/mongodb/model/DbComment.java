package com.discussion.portal.mongodb.model;


import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Document(collection="comments")
public class DbComment {
	
	@Id
	private String commentId;
	private String userId;
	private String comment;
	private Date date;
	private List<String> Agree;
	private List<String> Disagree;
}
