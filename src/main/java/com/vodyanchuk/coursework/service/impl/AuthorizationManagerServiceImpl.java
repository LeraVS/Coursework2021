package com.vodyanchuk.coursework.service.impl;

import com.vodyanchuk.coursework.exception.ResourceNotFoundException;
import com.vodyanchuk.coursework.model.AuthorizationManager;
import com.vodyanchuk.coursework.model.Client;
import com.vodyanchuk.coursework.model.TemporaryUser;
import com.vodyanchuk.coursework.model.enums.Role;
import com.vodyanchuk.coursework.model.enums.Status;
import com.vodyanchuk.coursework.repository.AuthorizationManagerRepository;
import com.vodyanchuk.coursework.service.AuthorizationManagerService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorizationManagerServiceImpl implements AuthorizationManagerService {
    private final AuthorizationManagerRepository authorizationManagerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthorizationManagerServiceImpl(AuthorizationManagerRepository authorizationManagerRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authorizationManagerRepository = authorizationManagerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public AuthorizationManager preSaveOperation(TemporaryUser user, Client client) {
        AuthorizationManager authorizationManager = new AuthorizationManager();
        authorizationManager.setUsername(user.getEmail());
        authorizationManager.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        authorizationManager.setRole(Role.USER);
        authorizationManager.setStatus(Status.ACTIVE);
        authorizationManager.setClient(client);
        return authorizationManager;
    }

    @Override
    public AuthorizationManager save(AuthorizationManager authorizationManager) {
        return authorizationManagerRepository.save(authorizationManager);
    }

    @Override
    public AuthorizationManager update(Long id, AuthorizationManager authorizationManager) {
        return authorizationManagerRepository.findById(id).map(authorizationManager1 -> {
            authorizationManager1.setUsername(authorizationManager.getUsername());
            authorizationManager1.setPassword(authorizationManager.getPassword());
            authorizationManager1.setClient(authorizationManager.getClient());
            return authorizationManagerRepository.save(authorizationManager1);
        }).orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));
    }

    @Override
    public void delete(Long id) {
        authorizationManagerRepository.deleteById(id);
    }

    @Override
    public AuthorizationManager findById(Long id) {
        return authorizationManagerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));
    }

    @Override
    public AuthorizationManager findByClientIdClient(Long id) {
        return authorizationManagerRepository.findByClientIdClient(id);
    }

    @Override
    public AuthorizationManager findByPasswordAndLogin(String password, String login) {
        return authorizationManagerRepository.findByPasswordAndUsername(password, login);
    }

    @Override
    public List<AuthorizationManager> findAll() {
        return authorizationManagerRepository.findAll();
    }
}
