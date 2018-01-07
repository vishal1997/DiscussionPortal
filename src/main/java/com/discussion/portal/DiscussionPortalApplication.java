package com.discussion.portal;

import javax.servlet.Filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import com.discussion.portal.utils.Json;

/**
 * 
 * @author Vishal
 *
 */

@SpringBootApplication
@EnableWebSecurity
@Configuration
@CrossOrigin(origins = "http://localhost:3410", maxAge = 3600)
public class DiscussionPortalApplication extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationHandler authHandler;
	
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
        .passwordEncoder(passwordEncoder());                                       
    }
    
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	http.csrf().disable();
    	http.addFilterBefore(simpleCORSFilter(), ChannelProcessingFilter.class);
    	http.cors().and()
    		.authorizeRequests()
    	
    		.antMatchers("/api/v1/register/**").permitAll()
    		.antMatchers("/api/v1/reset/**").permitAll()
    		.antMatchers("/api/v1/**").authenticated()
    		.anyRequest().permitAll()
    		    		
    		.and()
    		.formLogin()
    		.loginPage("/userloginpage").passwordParameter("password").usernameParameter("username")
    		.successHandler(authHandler.successHandler())
            .failureHandler(authHandler.failureHandler())
    		.permitAll()
    		
            .and()
            .exceptionHandling()
            .accessDeniedHandler(authHandler.accessDeniedHandler())
            .authenticationEntryPoint(authHandler.authenticationEntryPoint())
            
            .and()
            .logout().logoutUrl("/userlogoutpage")
            .logoutSuccessHandler(authHandler.logoutSuccessHandler())
            .permitAll();
    }
     
    @Bean
    public FilterRegistrationBean someFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(simpleCORSFilter());
        registration.addUrlPatterns("/*");
        registration.setName("simpleCORSFilter");
        registration.setOrder(1);
        return registration;
    } 

    @Bean(name = "simpleCORSFilter")
    public Filter simpleCORSFilter() {
        return new SimpleCORSFilter();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
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

