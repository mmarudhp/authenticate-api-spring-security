## Spring Security Authentication
This project mainly deals about protecting the end points using Spring Security framework

### Required Components

* ApplicationSecurityConfig : This is the configuration class where the security is enabled and the required beans are injected.
* AppAuthenticationFilter : This is the filter extends OncePerRequestFilter and intercepts the request and sets the authenticated status in SecurityContext
* AppAuthenticationProvider : This is the provider where the token will be validated via Service
* AppAuthenticationService : This is the actual where the business logic for validating the token is implemented
* AppAuthenticationToken : This is the token component which extends AbstractAuthenticationToken for holding token information  
