package com.discussion.portal.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.discussion.portal.mongodb.model.DbOtp;

public interface OtpRepository extends MongoRepository<DbOtp, String>{
	
}
