package com.vodyanchuk.coursework.service;

import com.vodyanchuk.coursework.model.Client;
import com.vodyanchuk.coursework.model.TemporaryUser;

import java.util.List;

public interface ClientService {
    Client preSaveOperation(TemporaryUser user);
    Client save(Client client);
    Client update(Long id, Client client);
    void delete(Long id);
    Client findById(Long id);
    Client findByName(String name);
    Client findByEmail(String email);
    List<Client> findAll();
}
