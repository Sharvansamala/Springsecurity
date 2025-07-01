package org.spring.springjpa.securityspring.repository;

import org.spring.springjpa.securityspring.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRespository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
}
