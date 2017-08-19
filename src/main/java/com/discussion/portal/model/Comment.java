package com.discussion.portal.model;


import java.util.Date;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment extends AgreeDisagree {

	private String commentId;
	private String userId;
	private String comment;
	private Date date;

}
