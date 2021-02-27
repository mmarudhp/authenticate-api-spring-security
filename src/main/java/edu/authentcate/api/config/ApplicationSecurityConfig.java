package edu.authentcate.api.config;

import edu.authentcate.api.security.AppAuthenticationFilter;
import edu.authentcate.api.security.AppAuthenticationProvider;
import edu.authentcate.api.security.AppAuthenticationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${authentication.protected.endpoints}")
    private String protectedEndpoints;

    @Bean
    public AppAuthenticationFilter authenticationFilter() throws Exception {
        return new AppAuthenticationFilter(this.authenticationManager(), this.entryPoint());
    }

    @Bean
    public AppAuthenticationService authenticationService() {
        return new AppAuthenticationService();
    }

    @Bean
    public AppAuthenticationProvider authenticationProvider() {
        return new AppAuthenticationProvider(authenticationService());
    }

    private AuthenticationEntryPoint entryPoint() {
        return ((httpServletRequest, httpServletResponse, e) -> {
            httpServletResponse.setHeader("WW-Authenticate", "Token provided in Authorization Header");
            httpServletResponse.sendError(401, e.getMessage());
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .exceptionHandling().authenticationEntryPoint(this.entryPoint())
                .and()
                    .addFilterAfter(this.authenticationFilter(), BasicAuthenticationFilter.class)
                .authorizeRequests().antMatchers(protectedEndpoints.split(",")).authenticated()
                .anyRequest().permitAll();
    }
}
