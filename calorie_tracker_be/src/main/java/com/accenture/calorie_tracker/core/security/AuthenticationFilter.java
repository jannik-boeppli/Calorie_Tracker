package ch.livenet.localsponsorship.security;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import ch.livenet.localsponsorship.exception.MissingUserCredentialsException;
import ch.livenet.localsponsorship.model.AppUser;
import ch.livenet.localsponsorship.model.JWTBaseProperties;
import ch.livenet.localsponsorship.utils.JWTManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * The filter which is applied when the user attempts to log in with his credentials.
 */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private final AuthenticationProvider authenticationProvider;
  private final JWTManager jwtManager;


  /**
   * Constructor which initializes the necessary properties.
   *
   * @param authenticationProvider the provider which attempts the log in against the cognito
   *                               authentication server.
   * @param jwtManager             Required for creating the access- and refresh_token
   */
  public AuthenticationFilter(
      AuthenticationProvider authenticationProvider, JWTManager jwtManager) {
    this.authenticationProvider = authenticationProvider;
    this.jwtManager = jwtManager;
  }

  /**
   * Gets the request and extracts the credentials used for authentication.
   *
   * @param request  The HTTP request sent by the frontend.
   * @param response The HTTP response sent by the application.
   * @return The UsernamePasswordAuthenticationToken containing the passed credentials.
   * @throws AuthenticationException The exception thrown by the AuthenticationProvider
   */
  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
                                              HttpServletResponse response)
      throws AuthenticationException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    if (username == null || password == null) {
      throw new MissingUserCredentialsException();
    }
    return authenticationProvider.authenticate(
        new UsernamePasswordAuthenticationToken(username, password));
  }

  /**
   * Required for the application. Sets the AuthenticationManager automatically.
   *
   * @param authenticationManager The AuthenticationManager passed by the spring environment.
   */
  @Autowired
  @Override
  public void setAuthenticationManager(AuthenticationManager authenticationManager) {
    super.setAuthenticationManager(authenticationManager);
  }

  /**
   * Is called if the attemptAuthentication method throws an exception.
   *
   * @param request  The HTTP request.
   * @param response The response sent to the user.
   * @param failed   the Exception which was thrown.
   * @throws IOException might be called by the ObjectMapper.
   */
  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            AuthenticationException failed)
      throws IOException {
    response.setStatus(400);
    response.setContentType(APPLICATION_JSON_VALUE);
    new ObjectMapper().writeValue(response.getOutputStream(),
        failed.getMessage());
  }

  /**
   * Creates the access_token and refresh_token, which are then sent back to the frontend.
   *
   * @param request    The HTTP request initially sent by the frontend.
   * @param response   The HTTP response initially sent by the application.
   * @param chain      The filter chain, which is automatically passed by spring.
   * @param authResult the resulting token, which was created by the AuthenticationProvider
   * @throws IOException Exception thrown by ObjectMapper.writeValue
   */
  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                          FilterChain chain, Authentication authResult)
      throws IOException {
    AppUser user = (AppUser) authResult.getPrincipal();
    Credentials credentials = (Credentials) authResult.getCredentials();


    JWTBaseProperties jwtBaseProperties =
        new JWTBaseProperties(user, credentials, request.getRequestURL().toString());
    String accessToken = jwtManager.createAccessToken(jwtBaseProperties);
    String refreshToken = jwtManager.createRefreshToken(jwtBaseProperties);

    response.setContentType(APPLICATION_JSON_VALUE);
    new ObjectMapper().writeValue(response.getOutputStream(),
        Map.of("access_token", accessToken, "refresh_token", refreshToken));
  }
}