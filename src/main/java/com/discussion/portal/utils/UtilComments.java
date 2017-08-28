package com.discussion.portal.utils;

import org.springframework.stereotype.Component;

import com.discussion.portal.model.Comment;
import com.discussion.portal.mongodb.model.DbComment;

@Component
public class UtilComments {

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
	
	public Comment convertDbCommentToComment(DbComment dbComment) {
		
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
		return comment;
	}
}
