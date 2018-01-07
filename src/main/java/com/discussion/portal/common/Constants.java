package com.discussion.portal.common;

/**
 * 
 * @author Vishal
 *
 */
public class Constants {

	/**
	 * 
	 * @author Vishal
	 *
	 */
	public class Database {
		public static final String DATABASE = "portal";
		public static final String ADDRESS = "ds115493.mlab.com";
		public static final int PORT = 15493;
		
		//Move this to properties file.
		public static final String USER = "akgupt";
		public static final String PWD = "akgupt";
	}
	
	public class StatusCode {
		public static final String SUCCESS = "Success";
		public static final String DUPLICATE = "Duplicate";
		public static final String ERROR = "Delete";
		public static final String INVALID = "Invalid";
		public static final String FAILED = "Failed";
	}
	
	public class MongoDbSignature {
		public static final String DUPLICATE_CODE = "error code 11000";
	}
	
	public class Opinion {
		public static final String AGREE = "Agree";
		public static final String DISAGREE = "Disagree";
	}
	
	public class Mailer {
		public static final String HOST = "smtp.gmail.com";
		public static final String SENDER = "anque.in@gmail.com";
		public static final String RESETSUBJECT = "Anque Reset Password";
		public static final String MAILSENT = " Mail sent";
		public static final String RESETPASSWORDBODY = "Your OTP for reset password : ";
		public static final String PA = "anquebyvishal";
	}
}
