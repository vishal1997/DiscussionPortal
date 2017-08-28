package com.discussion.portal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.context.request.RequestContextListener;

import com.discussion.portal.utils.Json;

@SpringBootApplication
@EnableWebSecurity
@Configuration
public class DiscussionPortalApplication extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	static Logger log = LoggerFactory.getLogger(DiscussionPortalApplication.class);
	@Bean
	public RequestContextListener requestContextListener() {
	    return new RequestContextListener();
	}  

    private static final String CSRF_HEADER_NAME = "XSRF-TOKEN";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
        .userDetailsService(userDetailsService)                   
        .passwordEncoder(getPasswordEncoded());                                             
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	http.csrf().disable();
    	http.authorizeRequests()
    		.anyRequest().authenticated()
    		.and()
    		.formLogin().permitAll();
    }
    
    
    
    private PasswordEncoder getPasswordEncoded() {
    	return new PasswordEncoder() {
    		@Override
    		public String encode(CharSequence charSequence) {
    			return charSequence.toString();
    		}
    		
    		@Override
    		public boolean matches(CharSequence charSequence, String s) {
    			return true;
    		}
    	};
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        log.info(Json.toJson(repository));
        repository.setHeaderName(CSRF_HEADER_NAME);
        return repository;
    }
	
	public static void main(String[] args) {
		SpringApplication.run(DiscussionPortalApplication.class, args);
	}
}

