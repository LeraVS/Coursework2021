package com.vodyanchuk.coursework.service;

import com.vodyanchuk.coursework.model.CalculationHistory;
import com.vodyanchuk.coursework.model.Client;
import com.vodyanchuk.coursework.model.enums.TypeOfTax;

import java.util.List;

public interface CalculationHistoryService {
    CalculationHistory preSaveOperation(Client client, TypeOfTax typeOfTax, double tax);
    CalculationHistory save(CalculationHistory calculationHistory);
    CalculationHistory update(Long id, CalculationHistory calculationHistory);
    void delete(Long id);
    CalculationHistory findById(Long id);
    List<CalculationHistory> findByClientIdClient(Long id);
    List<CalculationHistory> findAll();
}
