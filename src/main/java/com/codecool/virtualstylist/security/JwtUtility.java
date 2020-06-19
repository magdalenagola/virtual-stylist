package com.codecool.virtualstylist.security;


import com.codecool.virtualstylist.user.UserDetailsImpl;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import io.jsonwebtoken.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class JwtUtility {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtility.class);
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String TOKEN_SEPARATOR = " ";

    @Value("${virtualstylist.app.jwtSecret}")
    private String jwtSecret;

    @Value("${virtualstylist.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getEmail()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.info("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.info("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.info("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.info("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.info("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public Optional<String> getAccessToken(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
            return Optional.of(Arrays.asList(request.getHeader(AUTHORIZATION_HEADER).split(TOKEN_SEPARATOR)).get(1));
        }
        return Optional.empty();
    }
}
