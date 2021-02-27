package edu.authentcate.api.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Slf4j
public class AppAuthenticationProvider implements AuthenticationProvider {

    private AppAuthenticationService authenticationService;

    public AppAuthenticationProvider(AppAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!this.supports(authentication.getClass())) {
            log.error("Not Supported for : {}", authentication.getClass());
            return null;
        }
        log.info("Hello AuthenticationProvider ... ");
        return this.authenticationService.authenticate(authentication);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(AppAuthenticationToken.class);
    }
}
