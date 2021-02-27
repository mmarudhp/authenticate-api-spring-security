package edu.authentcate.api.security;

import edu.authentcate.api.model.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class AppAuthenticationToken extends AbstractAuthenticationToken {

    private AppToken appToken;
    private User user;

    public AppAuthenticationToken(AppToken appToken) {
        super(new ArrayList<>());
        this.appToken = appToken;
        this.user = null;
        this.setAuthenticated(false);
    }

    public AppAuthenticationToken setUser(User user) {
        this.user = user;
        this.setAuthenticated(user.isValid());
        return this;
    }

    @Override
    public AppToken getCredentials() {
        return this.appToken;
    }

    @Override
    public User getPrincipal() {
        return user;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.getPrincipal().getRoles().stream().map("ROLE_"::concat).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
