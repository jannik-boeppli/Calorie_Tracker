package ch.livenet.localsponsorship.security;


import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import ch.livenet.localsponsorship.model.AppUser;
import ch.livenet.localsponsorship.model.Roles;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Processes the authorization header in a request and extracts the authorities of a user.
 */
public class AuthorizationFilter extends OncePerRequestFilter {
  private final String clientId;
  private final String secret;

  public AuthorizationFilter(String clientId, String secret) {
    this.clientId = clientId;
    this.secret = secret;
  }

  /**
   * The processing of the request. If the request path is "auth/login", the request will be passed
   * to the next filter, the authentication filter. If the request doesn't contain the necessary
   * Authorization header, the request will also be passed down the filter chain.
   *
   * @param request  The HTTP request sent by the frontend.
   * @param response The HTTP response sent by the application.
   * @param filterChain The filter chain, which is automatically passed by spring.
   * @throws ServletException The Exception which might be thrown when passing down the request
   *     through the filter chain.
   * @throws IOException The Exception which might be thrown when passing down the request
   *     through the filter chain.
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    String authorizationHeader = request.getHeader(AUTHORIZATION);
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      try {
        String token = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Roles[] roles = decodedJWT.getClaim("roles").asArray(Roles.class);
        ArrayList<Roles> rolesArrayList = new ArrayList<>();
        stream(roles).forEach(role -> {
          rolesArrayList.add(role);
          authorities.add(role.getAuthority());
        });
        SecurityContextHolder.getContext().setAuthentication(
            new AuthenticationToken(new AppUser(decodedJWT.getSubject(), rolesArrayList), null,
                authorities));
        filterChain.doFilter(request, response);
      } catch (Exception e) {
        response.setStatus(FORBIDDEN.value());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(),
            Map.of("error_message", e.getMessage()));
      }
    } else {
      filterChain.doFilter(request, response);
    }
  }

  /**
   * Prevents the endpoints related to authorization from being filtered.
   *
   * @param request The HTTP Request.
   * @return true if the request shouldn't be filtered.
   */
  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    return request.getRequestURI().matches("/auth/.*");
  }
}