package edu.authentcate.api.security;

import edu.authentcate.api.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;

import java.util.Arrays;

@Slf4j
public class AppAuthenticationService {

    public AppAuthenticationToken authenticate(Authentication authentication) {
        AppToken token = ((AppAuthenticationToken)authentication).getCredentials();
        log.info("Validating the incoming token : {} ", token.getIncomingToken());

        Integer tokenValue = Integer.valueOf(token.getIncomingToken());
        log.info("Validating the incoming token integer : {} ", tokenValue);
        AppAuthenticationToken appAuthenticationToken = new AppAuthenticationToken(token);
        User user = User.builder()
                .userId(Long.valueOf(token.getIncomingToken()))
                .firstName("FirstName")
                .lastName("LastName")
                .roles(tokenValue % 2 == 0 ? Arrays.asList("ADMIN") : Arrays.asList("CASHIER"))
                .build();

        log.info("User : {} ", user);
        appAuthenticationToken.setUser(user);
        return appAuthenticationToken;
    }
}
