package com.ducdpg.employee_demo.services;

import com.ducdpg.employee_demo.enums.TokenType;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface IJwtService {
    String generateAccessToken(Map<String, Object> extraClaims, String email);
    boolean isAccessToken(String token);
    boolean isValidToken(String token, UserDetails userDetails, TokenType tokenType);
    boolean isTokenExpired(String token, TokenType tokenType);
    String extractEmail(String token, TokenType tokenType);
}
