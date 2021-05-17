package com.vodyanchuk.coursework.service;

import com.vodyanchuk.coursework.model.ObjectType;

import java.util.List;

public interface ObjectTypeService {
    ObjectType save(ObjectType objectType);
    ObjectType update(Long id, ObjectType objectType);
    void delete(Long id);
    ObjectType findById(Long id);
    List<ObjectType> findAll();
}
