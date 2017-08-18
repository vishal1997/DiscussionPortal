package com.discussion.portal.model;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {

	private String answerId;
	private String userId;
	private String comment;
	private Date date;
	private List<String> Agree;
	private List<String> Disagree;
}
