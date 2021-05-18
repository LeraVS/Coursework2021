package com.vodyanchuk.coursework.service.impl;

import com.vodyanchuk.coursework.exception.ResourceNotFoundException;
import com.vodyanchuk.coursework.model.CalculationHistory;
import com.vodyanchuk.coursework.model.Client;
import com.vodyanchuk.coursework.model.enums.TypeOfTax;
import com.vodyanchuk.coursework.repository.CalculationHistoryRepository;
import com.vodyanchuk.coursework.service.CalculationHistoryService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CalculationHistoryServiceImpl implements CalculationHistoryService {
    private final CalculationHistoryRepository calculationHistoryRepository;

    public CalculationHistoryServiceImpl(CalculationHistoryRepository calculationHistoryRepository) {
        this.calculationHistoryRepository = calculationHistoryRepository;
    }

    @Override
    public  CalculationHistory preSaveOperation(Client client, TypeOfTax typeOfTax, double tax) {
        CalculationHistory calculationHistory = new CalculationHistory();
        calculationHistory.setClient(client);
        calculationHistory.setDate(new Date());
        calculationHistory.setTypeOfTax(typeOfTax);
        calculationHistory.setTax(tax);
        return calculationHistory;
    }

    @Override
    public CalculationHistory save(CalculationHistory calculationHistory) {
        return calculationHistoryRepository.save(calculationHistory);
    }

    @Override
    public CalculationHistory update(Long id, CalculationHistory calculationHistory) {
        return calculationHistoryRepository.findById(id).map(calculationHistory1 -> {
            calculationHistory1.setClient(calculationHistory.getClient());
            calculationHistory1.setDate(calculationHistory.getDate());
            calculationHistory1.setTypeOfTax(calculationHistory.getTypeOfTax());
            calculationHistory1.setTax(calculationHistory.getTax());
            return calculationHistoryRepository.save(calculationHistory1);
        }).orElseThrow(() -> new ResourceNotFoundException("История не найдена"));
    }

    @Override
    public void delete(Long id) {
        calculationHistoryRepository.deleteById(id);
    }

    @Override
    public CalculationHistory findById(Long id) {
        return calculationHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("История не найдена"));
    }

    @Override
    public List<CalculationHistory> findByClientIdClient(Long id){
        return calculationHistoryRepository.findByClientIdClient(id);
    }

    @Override
    public List<CalculationHistory> findAll() {
        return calculationHistoryRepository.findAll();
    }
}
