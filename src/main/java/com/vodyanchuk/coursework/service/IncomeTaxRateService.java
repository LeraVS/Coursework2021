package com.vodyanchuk.coursework.service;

import com.vodyanchuk.coursework.model.IncomeTaxRate;
import com.vodyanchuk.coursework.model.TemporaryIncomeTaxRate;

import java.util.List;

public interface IncomeTaxRateService {
    IncomeTaxRate save(IncomeTaxRate incomeTaxRate);
    IncomeTaxRate update(Long id, IncomeTaxRate incomeTaxRate);
    void delete(Long id);
    IncomeTaxRate findById(Long id);
    List<IncomeTaxRate> findAll();
    IncomeTaxRate findByCriteria(String criteria);

    TemporaryIncomeTaxRate calculateRate(TemporaryIncomeTaxRate requiredRate);
}
