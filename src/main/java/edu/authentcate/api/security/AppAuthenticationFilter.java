package edu.authentcate.api.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AppAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHENTICATION_TOKEN_HEADER = "X-Authorization-Token";

    private AuthenticationManager authenticationManager;
    private AuthenticationEntryPoint authenticationEntryPoint;

    public AppAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationManager = authenticationManager;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader(AUTHENTICATION_TOKEN_HEADER);
        log.info("Incoming Token Header Value : {}", token);

        if (ObjectUtils.isEmpty(token)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            try {
                this.doAuthentication(new AppToken(token));
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } catch (AuthenticationException e) {
                this.authenticationEntryPoint.commence(httpServletRequest, httpServletResponse, e);
            }
        }
    }

    private void doAuthentication(AppToken appToken) {
        log.info("doAuthentication.... ");
        Authentication authentication = this.authenticationManager.authenticate(new AppAuthenticationToken(appToken));
        if (authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
}
