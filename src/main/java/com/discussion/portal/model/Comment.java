package com.discussion.portal.model;


import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment extends AgreeDisagree {

	private String answerId;
	private String commentId;
	private String userId;
	private String comment;
	private Date date;

}
