package com.vodyanchuk.coursework.service.impl;

import com.vodyanchuk.coursework.exception.ResourceNotFoundException;
import com.vodyanchuk.coursework.model.SingleTaxRate;
import com.vodyanchuk.coursework.model.TemporarySingleTaxRate;
import com.vodyanchuk.coursework.repository.SingleTaxRateRepository;
import com.vodyanchuk.coursework.service.SingleTaxRateService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SingleTaxRateServiceImpl implements SingleTaxRateService {
    private final SingleTaxRateRepository singleTaxRateRepository;

    public SingleTaxRateServiceImpl(SingleTaxRateRepository singleTaxRateRepository) {
        this.singleTaxRateRepository = singleTaxRateRepository;
    }

    @Override
    public SingleTaxRate save(SingleTaxRate singleTaxRate) {
        return singleTaxRateRepository.save(singleTaxRate);
    }

    @Override
    public SingleTaxRate update(Long id, SingleTaxRate singleTaxRate) {
        return null;
    }

    @Override
    public void delete(Long id) {
        singleTaxRateRepository.deleteById(id);
    }

    @Override
    public SingleTaxRate findById(Long id) {
        return singleTaxRateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ставка не найдена"));
    }

    @Override
    public List<SingleTaxRate> findAll() {
        return singleTaxRateRepository.findAll();
    }

    @Override
    public SingleTaxRate findByTypeOfBusinessIdTypeOfBusinessAndCity(Long idTypeOfBusiness, String city) {
        return singleTaxRateRepository.findByTypeOfBusinessIdTypeOfBusinessAndTradeLocationIdTradeLocationAndObjectTypeIdObjectTypeAndCity(
                idTypeOfBusiness, null, null, city);
    }

    @Override
    public SingleTaxRate findByTypeOfBusinessIdTypeOfBusinessAndTradeLocationIdTradeLocationAndCity(Long idTypeOfBusiness, Long idTradeLocation, String city) {
        return singleTaxRateRepository.findByTypeOfBusinessIdTypeOfBusinessAndTradeLocationIdTradeLocationAndObjectTypeIdObjectTypeAndCity(
                idTypeOfBusiness, idTradeLocation, null, city);
    }

    @Override
    public SingleTaxRate findByTypeOfBusinessIdTypeOfBusinessAndObjectTypeIdObjectTypeAndCity(Long idTypeOfBusiness, Long idObjectType, String city) {
        return singleTaxRateRepository.findByTypeOfBusinessIdTypeOfBusinessAndTradeLocationIdTradeLocationAndObjectTypeIdObjectTypeAndCity(
                idTypeOfBusiness, null, idObjectType, city);
    }

    @Override
    public SingleTaxRate findByTypeOfBusinessIdTypeOfBusinessAndTradeLocationIdTradeLocationAndObjectTypeIdObjectTypeAndCity(Long idTypeOfBusiness, Long idTradeLocation, Long idObjectType, String city) {
        return singleTaxRateRepository.findByTypeOfBusinessIdTypeOfBusinessAndTradeLocationIdTradeLocationAndObjectTypeIdObjectTypeAndCity(
                idTypeOfBusiness, idTradeLocation, idObjectType, city);
    }

    @Override
    public TemporarySingleTaxRate calculateRate(TemporarySingleTaxRate requiredRate) {
        if (requiredRate.getIdTradeLocation() == null && requiredRate.getIdObjectType() == null)
            requiredRate.setTax(findByTypeOfBusinessIdTypeOfBusinessAndCity(requiredRate.getIdTypeOfBusiness(), requiredRate.getCity()).getTax());
        if (requiredRate.getIdTradeLocation() != null && requiredRate.getIdObjectType() == null)
            requiredRate.setTax(findByTypeOfBusinessIdTypeOfBusinessAndTradeLocationIdTradeLocationAndCity(requiredRate.getIdTypeOfBusiness(), requiredRate.getIdTradeLocation(), requiredRate.getCity()).getTax());
        if (requiredRate.getIdTradeLocation() == null && requiredRate.getIdObjectType() != null)
            requiredRate.setTax(findByTypeOfBusinessIdTypeOfBusinessAndObjectTypeIdObjectTypeAndCity(requiredRate.getIdTypeOfBusiness(), requiredRate.getIdObjectType(), requiredRate.getCity()).getTax());
        if (requiredRate.getIdTradeLocation() != null && requiredRate.getIdObjectType() != null)
            requiredRate.setTax(findByTypeOfBusinessIdTypeOfBusinessAndTradeLocationIdTradeLocationAndObjectTypeIdObjectTypeAndCity(requiredRate.getIdTypeOfBusiness(), requiredRate.getIdTradeLocation(), requiredRate.getIdObjectType(), requiredRate.getCity()).getTax());
        return requiredRate;
    }
}
