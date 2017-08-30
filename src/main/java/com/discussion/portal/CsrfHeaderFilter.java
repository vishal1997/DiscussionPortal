package com.discussion.portal;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import com.discussion.portal.utils.Json;

/**
 * 
 * @author Vishal
 *
 */
public class CsrfHeaderFilter extends OncePerRequestFilter {

    private static final String CSRF_COOKIE_NAME = "XSRF-TOKEN";

	static Logger log = LoggerFactory.getLogger(CsrfHeaderFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    	
        CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        log.info("\nCSRF\n" + Json.toJson(csrf));
        if (csrf != null) {

            Cookie cookie = WebUtils.getCookie(request, CSRF_COOKIE_NAME);
            String token = csrf.getToken();

            if (cookie == null || token != null && !token.equals(cookie.getValue())) {
                cookie = new Cookie(CSRF_COOKIE_NAME, token);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }

        filterChain.doFilter(request, response);
    }
}