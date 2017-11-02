package com.discussion.portal.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.discussion.portal.model.Comment;
import com.discussion.portal.mongodb.model.DbComment;

/**
 * 
 * @author Vishal
 *
 */
@Component
public class CommentUtils {
	
	@Autowired
	private UserUtils userUtils;

	public DbComment convertCommentToDbComment(Comment commentObj) {
		
		if(commentObj == null) {
			return null;
		}
		DbComment dbComment = DbComment.builder()
							.comment(commentObj.getComment())
							.date(commentObj.getDate())
							.userId(commentObj.getUserId())
							.commentId(commentObj.getCommentId())
							.answerId(commentObj.getAnswerId())
							.build();
		dbComment.setAgree(commentObj.getAgree());
		dbComment.setDisagree(commentObj.getDisagree());
		return dbComment;
	}
	
	public Comment convertDbCommentToComment(DbComment dbComment, String name) {
		
		if(dbComment == null) {
			return null;
		}
		Comment comment = new Comment();
		comment.setAgree(dbComment.getAgree());
		comment.setAnswerId(dbComment.getAnswerId());
		comment.setComment(dbComment.getComment());
		comment.setCommentId(dbComment.getCommentId());
		comment.setDate(dbComment.getDate());
		comment.setDisagree(dbComment.getDisagree());
		comment.setNoOfAgree(dbComment.getNoOfAgree());
		comment.setNoOfDisagree(dbComment.getNoOfDisagree());
		comment.setUserId(dbComment.getUserId());
		comment.setName(name);
		if(comment.getAgree() != null) {
			comment.setAgreeStatus(dbComment.getAgree().contains(userUtils.getCurrentUser()));
		} else {
			comment.setAgreeStatus(false);
		}
		if(comment.getDisagree() != null) {
			comment.setDisagreeStatus(dbComment.getDisagree().contains(userUtils.getCurrentUser()));
		} else {
			comment.setDisagreeStatus(false);
		}
		return comment;
	}
}
