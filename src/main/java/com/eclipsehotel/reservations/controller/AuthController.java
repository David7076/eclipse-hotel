package com.eclipsehotel.reservations.controller;

import com.eclipsehotel.reservations.controller.dto.auth.LoginRequestDTO;
import com.eclipsehotel.reservations.controller.dto.auth.TokenResponseDTO;
import com.eclipsehotel.reservations.domain.services.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IUserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid LoginRequestDTO request) {
        TokenResponseDTO token = userService.authenticate(request);
        return ResponseEntity.ok(token);
    }

}
