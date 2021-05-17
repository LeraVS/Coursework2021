package com.vodyanchuk.coursework.service;

import com.vodyanchuk.coursework.model.AuthorizationManager;
import com.vodyanchuk.coursework.model.Client;
import com.vodyanchuk.coursework.model.TemporaryUser;

import java.util.List;

public interface AuthorizationManagerService {
    AuthorizationManager preSaveOperation(TemporaryUser user, Client client);
    AuthorizationManager save(AuthorizationManager authorizationManager);
    AuthorizationManager update(Long id, AuthorizationManager authorizationManager);
    void delete(Long id);
    AuthorizationManager findById(Long id);
    AuthorizationManager findByClientIdClient(Long id);
    AuthorizationManager findByPasswordAndLogin(String password, String login);
    List<AuthorizationManager> findAll();
}
