package com.eclipsehotel.reservations.infra.security.service;


import org.springframework.security.core.userdetails.UserDetails;

public interface ITokenService {
    String generateToken(UserDetails userDetails);

    String extractUsername(String token);

    boolean isTokenValid(String token, UserDetails userDetails);
}
