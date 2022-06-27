package ch.livenet.localsponsorship.security;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import ch.livenet.localsponsorship.utils.CognitoProperties;
import ch.livenet.localsponsorship.utils.JWTManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final CognitoProperties cognitoProps;
  private final JWTManager jwtManager;
  private final CognitoAuthenticationProvider authenticationProvider;

  /**
   * Initializes the Object by injecting the required beans.
   *
   * @param cognitoProps           The props which are associated with the cognito server
   * @param jwtManager             The jwt manager
   * @param authenticationProvider Used for the authentication with Cognito
   */
  public SecurityConfiguration(CognitoProperties cognitoProps, JWTManager jwtManager,
                               CognitoAuthenticationProvider authenticationProvider) {
    this.cognitoProps = cognitoProps;
    this.jwtManager = jwtManager;
    this.authenticationProvider = authenticationProvider;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authenticationProvider);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    AuthenticationFilter authenticationFilter =
        new AuthenticationFilter(authenticationProvider, jwtManager);
    authenticationFilter.setFilterProcessesUrl("/auth/login");
    http.csrf().disable();
    http.cors();
    http.sessionManagement().sessionCreationPolicy(STATELESS);
    http.authorizeRequests().antMatchers("/auth/**").permitAll();
    http.authorizeRequests().anyRequest().authenticated();
    http.addFilter(authenticationFilter);
    http.addFilterBefore(
        new AuthorizationFilter(cognitoProps.getClientId(), cognitoProps.getSecret()),
        UsernamePasswordAuthenticationFilter.class);
  }

}