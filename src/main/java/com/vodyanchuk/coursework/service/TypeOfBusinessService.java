package com.vodyanchuk.coursework.service;

import com.vodyanchuk.coursework.model.TypeOfBusiness;

import java.util.List;

public interface TypeOfBusinessService {
    TypeOfBusiness save(TypeOfBusiness typeOfBusiness);
    TypeOfBusiness update(Long id, TypeOfBusiness typeOfBusiness);
    void delete(Long id);
    TypeOfBusiness findById(Long id);
    List<TypeOfBusiness> findAll();
}
