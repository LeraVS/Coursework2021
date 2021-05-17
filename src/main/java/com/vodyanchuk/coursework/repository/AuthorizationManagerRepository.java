package com.vodyanchuk.coursework.repository;

import com.vodyanchuk.coursework.model.AuthorizationManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorizationManagerRepository extends JpaRepository<AuthorizationManager, Long> {
    AuthorizationManager findByClientIdClient(Long id);
    AuthorizationManager findByPasswordAndUsername(String password, String login);
    Optional<AuthorizationManager> findByUsername(String login);
}
