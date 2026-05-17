package com.eclipsehotel.reservations.domain.services.impl;

import com.eclipsehotel.reservations.controller.dto.auth.LoginRequestDTO;
import com.eclipsehotel.reservations.controller.dto.auth.TokenResponseDTO;
import com.eclipsehotel.reservations.controller.dto.user.UserRequestDTO;
import com.eclipsehotel.reservations.controller.dto.user.UserResponseDTO;
import com.eclipsehotel.reservations.domain.models.UserEntity;
import com.eclipsehotel.reservations.domain.services.IUserService;
import com.eclipsehotel.reservations.infra.repository.UserRepository;
import com.eclipsehotel.reservations.infra.security.service.ITokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ITokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserResponseDTO save(UserRequestDTO request) {
        return null;
    }

    @Override
    public TokenResponseDTO authenticate(LoginRequestDTO request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(), request.password()
                )
        );

        var user = (UserEntity) auth.getPrincipal();

        if(user == null) throw new UsernameNotFoundException("Usuario não encontrado! ");

        String token = tokenService.generateToken(user);
        return new TokenResponseDTO(token);
    }
}
