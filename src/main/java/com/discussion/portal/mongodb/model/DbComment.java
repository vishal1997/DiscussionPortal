package com.discussion.portal.mongodb.model;


import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DbComment {
	
	private String answerId;
	private String userId;
	private String comment;
	private Date date;
	private List<String> Agree;
	private List<String> Disagree;
}
