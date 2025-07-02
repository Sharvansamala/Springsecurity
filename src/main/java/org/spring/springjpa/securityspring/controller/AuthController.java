package org.spring.springjpa.securityspring.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.spring.springjpa.securityspring.dto.LoginDto;
import org.spring.springjpa.securityspring.dto.LoginResponseDto;
import org.spring.springjpa.securityspring.dto.SignUpDto;
import org.spring.springjpa.securityspring.dto.UserDto;
import org.spring.springjpa.securityspring.service.AuthService;
import org.spring.springjpa.securityspring.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;
    private final AuthService authService;

    @Value("${spring.security.secure}")
    private boolean isProduction; // Set this based on your environment

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto) {
        UserDto userDto = service.signUp(signUpDto);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) {
        LoginResponseDto token = authService.login(loginDto);

        Cookie cookie = new Cookie("refreshToken", token.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(isProduction); // only send cookie over HTTPS
        response.addCookie(cookie);

        return ResponseEntity.ok(token);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new AuthenticationServiceException("Refresh token not found in cookies"));
        if (refreshToken == null) {
            return ResponseEntity.status(401).build(); // Unauthorized
        }

        LoginResponseDto newTokens = authService.refreshTokens(refreshToken);
        Cookie cookie = new Cookie("refreshToken", newTokens.getRefreshToken());
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(newTokens);
    }
}
