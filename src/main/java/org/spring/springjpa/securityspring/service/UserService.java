package org.spring.springjpa.securityspring.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.spring.springjpa.securityspring.dto.SignUpDto;
import org.spring.springjpa.securityspring.dto.UserDto;
import org.spring.springjpa.securityspring.entity.UserEntity;
import org.spring.springjpa.securityspring.exception.ResourceNotFoundException;
import org.spring.springjpa.securityspring.repository.UserRespository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRespository userRespository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRespository.findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException("User not found with email: " + username));
    }

    public UserDto signUp(SignUpDto signUpDto) {
        Optional<UserEntity> user = userRespository.findByEmail(signUpDto.getEmail());
        if (user.isPresent()) {
            throw new ResourceNotFoundException("User already exists with email: " + signUpDto.getEmail());
        }
        UserEntity newUser = modelMapper.map(signUpDto, UserEntity.class);
        newUser.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        UserEntity savedUser = userRespository.save(newUser);

        return modelMapper.map(savedUser, UserDto.class);
    }

    public UserEntity getUserById(Long id) {
        return userRespository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }
}
