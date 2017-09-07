package com.discussion.portal.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.discussion.portal.dao.PortalDao;
import com.discussion.portal.helper.PortalHelper;
import com.discussion.portal.iter.StudentInfo;
import com.discussion.portal.model.User;
import com.discussion.portal.model.auth.AuthenticationModel;
import com.discussion.portal.model.auth.Details_;
import com.discussion.portal.mongodb.model.DbUser;
import com.discussion.portal.user.response.UserResponse;

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
	
	public User convertDbUserToUser(DbUser dbUser) {
		
		User user = new User();
		user.setUsername(dbUser.getUsername());
		user.setQuestionId(dbUser.getQuestionId());
		user.setUserAnswerMap(dbUser.getUserAnswerMap());
		
		return user;
	}
	
	
	public UserResponse convertDbUserToUserDetails(DbUser dbUser) {
		
		UserResponse user = new UserResponse();
		user.setAdmissionYear(dbUser.getAdmissionYear());
		user.setBranch(dbUser.getBranch());
		user.setCity(dbUser.getCity());
		user.setDob(dbUser.getDob());
		user.setEmailId(dbUser.getEmailId());
		user.setGender(dbUser.getGender());
		user.setName(dbUser.getName());
		user.setSec(dbUser.getSec());
		user.setState(dbUser.getState());
		user.setUserId(dbUser.getUsername());
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
