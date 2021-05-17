package com.vodyanchuk.coursework.service.impl;

import com.vodyanchuk.coursework.exception.ResourceNotFoundException;
import com.vodyanchuk.coursework.model.Client;
import com.vodyanchuk.coursework.model.TemporaryUser;
import com.vodyanchuk.coursework.repository.ClientRepository;
import com.vodyanchuk.coursework.service.ClientService;
import com.vodyanchuk.coursework.service.ObjectTypeService;
import com.vodyanchuk.coursework.service.TradeLocationService;
import com.vodyanchuk.coursework.service.TypeOfBusinessService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final TypeOfBusinessService typeOfBusinessService;
    private final TradeLocationService tradeLocationService;
    private final ObjectTypeService objectTypeService;

    public ClientServiceImpl(ClientRepository clientRepository, TypeOfBusinessService typeOfBusinessService, TradeLocationService tradeLocationService, ObjectTypeService objectTypeService) {
        this.clientRepository = clientRepository;
        this.typeOfBusinessService = typeOfBusinessService;
        this.tradeLocationService = tradeLocationService;
        this.objectTypeService = objectTypeService;
    }

    @Override
    public Client preSaveOperation(TemporaryUser user) {
        Client client = new Client();
        client.setName(user.getName());
        client.setAddress(user.getAddress());
        client.setEmail(user.getEmail());
        client.setTypeOfBusiness(typeOfBusinessService.findById(user.getIdTypeOfBusiness()));
        client.setTradeLocation(user.getIdTradeLocation() != null? tradeLocationService.findById(user.getIdTradeLocation()):null);
        client.setObjectType(user.getIdObjectType() != null? objectTypeService.findById(user.getIdObjectType()):null);
        client.setTypeOfTax(user.getTypeOfTax()!= null? user.getTypeOfTax():null);
        return client;
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client update(Long id, Client client) {
        return clientRepository.findById(id).map(client1 -> {
            client1.setName(client.getName());
            client1.setAddress(client.getAddress());
            client1.setEmail(client.getEmail());
            client1.setTypeOfBusiness(client.getTypeOfBusiness());
            return clientRepository.save(client1);
        }).orElseThrow(() -> new ResourceNotFoundException("Организация не найдена"));
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Организация не найдена"));
    }

    @Override
    public Client findByName(String name) {
        return clientRepository.findByName(name);
    }

    @Override
    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
}
