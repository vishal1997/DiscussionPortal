package com.discussion.portal.dao.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.discussion.portal.common.Constants.MongoDbSignature;
import com.discussion.portal.common.Constants.StatusCode;
import com.discussion.portal.dao.PortalDao;
import com.discussion.portal.model.Answer;
import com.discussion.portal.mongodb.model.DbAnswer;
import com.discussion.portal.mongodb.model.DbComment;
import com.discussion.portal.mongodb.model.DbQuestion;
import com.discussion.portal.mongodb.model.DbUser;
import com.discussion.portal.mongodb.repository.AnswerRepository;
import com.discussion.portal.mongodb.repository.CommentRepository;
import com.discussion.portal.mongodb.repository.QuestionsRepository;
import com.discussion.portal.mongodb.repository.UserRepository;
import com.discussion.portal.utils.Json;

@Component
public class DiscussionPortalDao implements PortalDao {

	@Autowired
	private QuestionsRepository quesRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AnswerRepository ansRepo;
	
	@Autowired
	private CommentRepository comRepo;

	@Autowired
	private MongoTemplate mongoTemplate;
	
	static Logger log = LoggerFactory.getLogger(DiscussionPortalDao.class);
	
	@Override
	public String insertQuestion(DbQuestion question) {

		try {
			log.info("\nTrying to insert Question with question details:\n" + Json.toJson(question));
			quesRepo.insert(question);
			
		} catch (Exception e) {
			log.error("\nException Occured\n" + Json.toJson(e));
			if(e.getCause().getMessage().contains(MongoDbSignature.DUPLICATE_CODE)) {
				return StatusCode.DUPLICATE;
			}
			throw new RuntimeException("Some error occured while saving the question", e);
		}
		return StatusCode.SUCCESS;
		
	}

	@Override
	public DbQuestion getQuestionById(String questionId) {
		String _questionId = questionId.toLowerCase();
		try {
			return quesRepo.findOne(_questionId);
		} catch (Exception e) {
			throw new RuntimeException("Error while finding the question", e);
		}
		
	}
	
	@Override
	public List<String> getQuestionIdsByUserId(String userId) {
		DbUser user = userRepo.findOne(userId);
		try {
			return user.getQuestionId();
		} catch (NullPointerException e) {
			throw new RuntimeException("User cannot be null Null", e);
		}
	}

	@Override
	public String addAnswer(DbAnswer answer) {
		
		try {
			ansRepo.insert(answer);
			log.info("\nAnswer successfully added to DB\n" + Json.toJson(answer));
			return StatusCode.SUCCESS;
			
		} catch (Exception e) {
			log.error("\nUnable to add answer to DB\n" + Json.toJson(e));
			return "{\"status\":\"fail\"}";
		}
	}
	
	public String addAnswerToQuestionMap(Answer answer) {
		
		try {
			log.info("\nTrying to find Question by QuestionId " + answer.getAnswerId());
			DbQuestion question = quesRepo.findOne(answer.getQuestionId());
			question.addAnswerMap(answer);
			log.info("\nTrying to update question with new answer\n" + Json.toJson(question));
			quesRepo.save(question);
			return StatusCode.SUCCESS;
			
		} catch (Exception e) {
			log.error("Exception occured" + Json.toJson(e));
			return "{\"status\":\"fail\"}";
		}
	}

	@Override
	public DbAnswer getAnswerById(String answerId) {
		
		try {
			return ansRepo.findOne(answerId);
		} catch (Exception e) {
			throw new RuntimeException("Error while finding the answer", e);
		}
	}

	@Override
	public List<DbAnswer> getAnswerByUserId(String userId) {
		
		Query query = new Query().addCriteria(Criteria.where("userId")
				.is(userId))
				.with(new Sort(Sort.Direction.DESC,"date"));

		List<DbAnswer> dbAnswer = mongoTemplate.find(query, DbAnswer.class);
		return dbAnswer;
	}

	@Override
	public List<DbAnswer> getFeeds() {
		Query query = new Query().with(new Sort(Sort.Direction.DESC, "date"));
		List<DbAnswer> resourceList = mongoTemplate.find(query, DbAnswer.class);
		return resourceList;
	}

	@Override
	public String updateDbAnswer(DbAnswer dbAnswer) {
		
		try {
			ansRepo.save(dbAnswer);
			log.info("\nupdated DbAnswer\n" + Json.toJson(dbAnswer));
			return StatusCode.SUCCESS;
		} catch(Exception e) {
			throw new RuntimeException("Error while updating DbAnswer", e);
		}
	}

	@Override
	public String addComments(DbComment dbComment) {
		
		try {
			comRepo.insert(dbComment);
			log.info("\nComment added\n" +Json.toJson(dbComment));
			return StatusCode.SUCCESS;
		} catch(Exception e) {
			throw new RuntimeException("Error while adding comment", e);
		}
	}

	@Override
	public DbComment getCommentById(String commentId) {
		
		try {
			return comRepo.findOne(commentId);
		} catch(Exception e) {
			throw new RuntimeException("Error while getting dbComment Object", e);
		}
	}

	@Override
	public String updateDbComment(DbComment dbComment) {
		
		try {
			comRepo.save(dbComment);
			return StatusCode.SUCCESS;
		} catch(Exception e) {
			throw new RuntimeException("Error while adding dbComment Object", e);
		}
	}
}
