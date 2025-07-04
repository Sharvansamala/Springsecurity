package org.spring.springjpa.securityspring;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecurityspringApplication {

    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure().load(); // Load environment variables from .env file

        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
        SpringApplication.run(SecurityspringApplication.class, args);
    }

}
