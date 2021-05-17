package com.vodyanchuk.coursework.service.impl;

import com.vodyanchuk.coursework.exception.ResourceNotFoundException;
import com.vodyanchuk.coursework.model.Client;
import com.vodyanchuk.coursework.model.ObjectType;
import com.vodyanchuk.coursework.repository.ObjectTypeRepository;
import com.vodyanchuk.coursework.service.ObjectTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjectTypeServiceImpl implements ObjectTypeService {
    private final ObjectTypeRepository objectTypeRepository;

    public ObjectTypeServiceImpl(ObjectTypeRepository objectTypeRepository) {
        this.objectTypeRepository = objectTypeRepository;
    }

    @Override
    public ObjectType save(ObjectType objectType) {
        return objectTypeRepository.save(objectType);
    }

    @Override
    public ObjectType update(Long id, ObjectType objectType) {
        return objectTypeRepository.findById(id).map(type -> {
            type.setName(objectType.getName());
            if (objectType.getClients() != null) {
                List<Client> clients = type.getClients();
                clients.clear();
                clients.addAll(objectType.getClients());
            }
            return objectTypeRepository.save(type);
        }).orElseThrow(() -> new ResourceNotFoundException("Тип объекта не найден"));
    }

    @Override
    public void delete(Long id) {
        objectTypeRepository.deleteById(id);
    }

    @Override
    public ObjectType findById(Long id) {
        return objectTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Тип объекта не найден"));
    }

    @Override
    public List<ObjectType> findAll() {
        return objectTypeRepository.findAll();
    }

 }
