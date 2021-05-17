package com.vodyanchuk.coursework.service;

import com.vodyanchuk.coursework.model.SingleTaxRate;
import com.vodyanchuk.coursework.model.TemporarySingleTaxRate;

import java.util.List;

public interface SingleTaxRateService {
    SingleTaxRate save(SingleTaxRate singleTaxRate);
    SingleTaxRate update(Long id, SingleTaxRate singleTaxRate);
    void delete(Long id);
    SingleTaxRate findById(Long id);
    List<SingleTaxRate> findAll();
    SingleTaxRate findByTypeOfBusinessIdTypeOfBusinessAndCity(Long idTypeOfBusiness, String city);
    SingleTaxRate findByTypeOfBusinessIdTypeOfBusinessAndTradeLocationIdTradeLocationAndCity(Long idTypeOfBusiness, Long idTradeLocation, String city);
    SingleTaxRate findByTypeOfBusinessIdTypeOfBusinessAndObjectTypeIdObjectTypeAndCity(Long idTypeOfBusiness, Long idObjectType, String city);
    SingleTaxRate findByTypeOfBusinessIdTypeOfBusinessAndTradeLocationIdTradeLocationAndObjectTypeIdObjectTypeAndCity(Long idTypeOfBusiness, Long idTradeLocation, Long idObjectType, String city);

    TemporarySingleTaxRate calculateRate(TemporarySingleTaxRate requiredRate);
}
