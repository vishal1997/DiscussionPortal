package com.discussion.portal.model;


import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Vishal
 *
 */
@Getter
@Setter
public class Comment extends AgreeDisagree {

	private String answerId;
	private String commentId;
	private String userId;
	private String comment;
	private Date date;
}
