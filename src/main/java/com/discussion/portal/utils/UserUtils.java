package com.discussion.portal.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Vishal
 *
 */
@Component
public class UserUtils {
	
	public String getCurrentUser() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
}
