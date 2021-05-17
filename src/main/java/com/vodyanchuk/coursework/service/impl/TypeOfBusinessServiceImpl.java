package com.vodyanchuk.coursework.service.impl;

import com.vodyanchuk.coursework.exception.ResourceNotFoundException;
import com.vodyanchuk.coursework.model.Client;
import com.vodyanchuk.coursework.model.TypeOfBusiness;
import com.vodyanchuk.coursework.repository.TypeOfBusinessRepository;
import com.vodyanchuk.coursework.service.TypeOfBusinessService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeOfBusinessServiceImpl implements TypeOfBusinessService {

    private final TypeOfBusinessRepository typeOfBusinessRepository;

    public TypeOfBusinessServiceImpl(TypeOfBusinessRepository typeOfBusinessRepository) {
        this.typeOfBusinessRepository = typeOfBusinessRepository;
    }

    @Override
    public TypeOfBusiness save(TypeOfBusiness typeOfBusiness) {
        return typeOfBusinessRepository.save(typeOfBusiness);
    }

    @Override
    public TypeOfBusiness update(Long id, TypeOfBusiness typeOfBusiness) {
        return typeOfBusinessRepository.findById(id).map(type -> {
            type.setName(typeOfBusiness.getName());
            if (typeOfBusiness.getClients() != null) {
                List<Client> clients = type.getClients();
                clients.clear();
                clients.addAll(typeOfBusiness.getClients());
            }
            return typeOfBusinessRepository.save(type);
        }).orElseThrow(() -> new ResourceNotFoundException("Вид деятельности не найден"));
    }

    @Override
    public void delete(Long id) {
        typeOfBusinessRepository.deleteById(id);
    }

    @Override
    public TypeOfBusiness findById(Long id) {
        return typeOfBusinessRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Вид деятельности не найден"));
    }

    @Override
    public List<TypeOfBusiness> findAll() {
        return typeOfBusinessRepository.findAll();
    }
}
