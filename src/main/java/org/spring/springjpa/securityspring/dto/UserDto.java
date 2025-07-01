package org.spring.springjpa.securityspring.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String name;
}
