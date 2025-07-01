package org.spring.springjpa.securityspring.service;

import lombok.RequiredArgsConstructor;
import org.spring.springjpa.securityspring.dto.LoginDto;
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

    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        UserEntity user = (UserEntity) authentication.getPrincipal();
        return jwtService.generateToken(user);
    }
}
