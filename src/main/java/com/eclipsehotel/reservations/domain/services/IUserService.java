package com.eclipsehotel.reservations.domain.services;

import com.eclipsehotel.reservations.controller.dto.auth.LoginRequestDTO;
import com.eclipsehotel.reservations.controller.dto.auth.TokenResponseDTO;
import com.eclipsehotel.reservations.controller.dto.user.UserRequestDTO;
import com.eclipsehotel.reservations.controller.dto.user.UserResponseDTO;

public interface IUserService {

    UserResponseDTO save(UserRequestDTO request);

    TokenResponseDTO authenticate(LoginRequestDTO request);

}
