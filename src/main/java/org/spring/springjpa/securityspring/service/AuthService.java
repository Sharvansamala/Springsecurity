package org.spring.springjpa.securityspring.service;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.spring.springjpa.securityspring.dto.LoginDto;
import org.spring.springjpa.securityspring.dto.LoginResponseDto;
import org.spring.springjpa.securityspring.entity.UserEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    public LoginResponseDto login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        UserEntity user = (UserEntity) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return new LoginResponseDto(user.getId(), accessToken, refreshToken);
    }


    public LoginResponseDto refreshTokens(String refreshToken) {

        Long userId = jwtService.getUserIdFromToken(refreshToken);
        if (userId == null) {
            throw new JwtException("Invalid refresh token");
        }
        UserEntity user = userService.getUserById(userId);
        String newAccessToken = jwtService.generateAccessToken(user);
        return new LoginResponseDto(user.getId(), newAccessToken, refreshToken);
    }
}
