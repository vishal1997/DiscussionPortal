package com.discussion.portal.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.discussion.portal.iter.StudentInfo;
import com.discussion.portal.model.User;
import com.discussion.portal.model.auth.AuthenticationModel;
import com.discussion.portal.model.auth.Details_;
import com.discussion.portal.mongodb.model.DbUser;

/**
 * 
 * @author Vishal
 *
 */
@Component
public class UserUtils {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public String getCurrentUser() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	public Authentication getCurrentUserFull() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public Details_ userNameIdPair() {
		return ((AuthenticationModel) Json.
				fromJson(Json
						.toJson(getCurrentUserFull()), AuthenticationModel.class))
				.getUserAuthentication()
				.getDetails();
	}
	
	
	public User convertDbUserToUser(DbUser dbUser) {
		
		User user = new User();
		user.setUsername(dbUser.getUsername());
		user.setQuestionId(dbUser.getQuestionId());
		user.setUserAnswerMap(dbUser.getUserAnswerMap());
		
		return user;
	}
	
	
	public User convertDbUserToUserDetails(DbUser dbUser) {
		
		User user = new User();
		user.setAdmissionYear(dbUser.getAdmissionYear());
		user.setBranch(dbUser.getBranch());
		user.setCity(dbUser.getCity());
		user.setDob(dbUser.getDob());
		user.setEmailId(dbUser.getEmailId());
		user.setGender(dbUser.getGender());
		user.setName(dbUser.getName());
		user.setSec(dbUser.getSec());
		user.setState(dbUser.getState());
		user.setUsername(dbUser.getUsername());
		return user;
		
	}
	
	public DbUser convertStudentInfoToDbUser(StudentInfo studentInfo, User user) {
		
		DbUser dbUser = new DbUser();
		
		dbUser.setPassword(passwordEncoder.encode(user.getPassword()));
		dbUser.setUsername(user.getUsername());
		dbUser.setAdmissionYear(studentInfo.getAdmissionyear());
		dbUser.setBranch(studentInfo.getBranchdesc());
		dbUser.setDob(studentInfo.getDateofbirth());
		dbUser.setName(studentInfo.getName());
		dbUser.setGender(studentInfo.getGender());
		dbUser.setEmailId(studentInfo.getPemailid());
		dbUser.setSec(studentInfo.getSectioncode());
		dbUser.setPhone(studentInfo.getScellno());
		dbUser.setState(studentInfo.getCstatename());
		dbUser.setCity(studentInfo.getCcityname());
		return dbUser;
	}
}
