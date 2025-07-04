package org.spring.springjpa.securityspring;

import org.junit.jupiter.api.Test;
import org.spring.springjpa.securityspring.entity.UserEntity;
import org.spring.springjpa.securityspring.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityspringApplicationTests {

    @Autowired
    JwtService jwtService;

    @Test
    void contextLoads() {
//        UserEntity user = new UserEntity(4L,"sharvan@gmail.com","Sharvan");
//        String token = jwtService.generateAccessToken(user);
//        System.out.println(token);
//
//        System.out.println("id: "+jwtService.getUserIdFromToken(token));
    }



}
