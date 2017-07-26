package com.discussion.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
@EnableOAuth2Sso
public class DiscussionPortalApplication extends WebSecurityConfigurerAdapter {
	
	@Bean
	public RequestContextListener requestContextListener() {
	    return new RequestContextListener();
	}  
	
	public static void main(String[] args) {
		SpringApplication.run(DiscussionPortalApplication.class, args);
	}
}

