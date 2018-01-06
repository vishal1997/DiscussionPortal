package com.discussion.portal.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.discussion.portal.mongodb.model.PasswordResetToken;

@Repository
public interface PasswordResetRepository extends MongoRepository<PasswordResetToken, String> {

    PasswordResetToken findByToken(String token);

}
