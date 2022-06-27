package com.accenture.calorie_tracker.core.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Processes the authorization header in a request and extracts the authorities of a user.
 */
public class AuthorizationFilter extends OncePerRequestFilter {
    private JWTManager jwtManager;

    public AuthorizationFilter(JWTManager jwtManager) {
        this.jwtManager = jwtManager;
    }

    /**
     * The processing of the request. If the request path is "auth/login", the request will be passed
     * to the next filter, the authentication filter. If the request doesn't contain the necessary
     * Authorization header, the request will also be passed down the filter chain.
     *
     * @param request     The HTTP request sent by the frontend.
     * @param response    The HTTP response sent by the application.
     * @param filterChain The filter chain, which is automatically passed by spring.
     * @throws ServletException The Exception which might be thrown when passing down the request
     *                          through the filter chain.
     * @throws IOException      The Exception which might be thrown when passing down the request
     *                          through the filter chain.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String token = authorizationHeader.substring("Bearer ".length());
                DecodedJWT decodedJWT = jwtManager.verifyToken(token);

                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(decodedJWT.getSubject(), null, new ArrayList<>()));
                filterChain.doFilter(request, response);
            } catch (Exception e) {
                response.setStatus(FORBIDDEN.value());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),
                        e.getMessage());
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