package com.librarian_tool.librarian_tool.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
/**
 * The {@class JwtFilter} is a custom security filter that processes incoming HTTP requests to authenticate users
 * based on a JWT (JSON Web Token) provided in the `Authorization` header.
 *
 * It extends {@link OncePerRequestFilter} to ensure that the filtering logic is executed once per request.
 *
 * The filter extracts the JWT from the `Authorization` header, validates it, and sets the authentication details in the
 * {@link SecurityContextHolder} if the token is valid.
 *
 * Workflow:
 *   1. Extracts the JWT token from the `Authorization` header.
 *   2. Parses the username from the token.
 *   3. Validates the token using {@link JWTService}.
 *   4. Sets the user authentication in the security context if validation is successful.
 *
 * @author Moaz Alqahtani
 * @version 1.0
 * @since 2024-Nov
 *
 */
@Component
public class JwtFilter extends OncePerRequestFilter{
    @Autowired
    private JWTService jwtService;
    @Autowired
    ApplicationContext context;

    /**
     * Processes incoming HTTP requests, extracts the JWT token from the `Authorization` header,
     * validates the token, and sets the user authentication in the security context.
     *
     * @param request     incoming {@link HttpServletRequest}.
     * @param response    outgoing {@link HttpServletResponse}.
     * @param filterChain {@link FilterChain} to pass the request to the next filter.
     * @throws ServletException if an error occurs during filtering.
     * @throws IOException      if an input or output error occurs.
     *
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Bearer Token
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            username = jwtService.extractUserName(token);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(username);
            if(jwtService.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);

            }
        }
        filterChain.doFilter(request,response);
    }
}
