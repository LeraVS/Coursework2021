package com.vodyanchuk.coursework.service;



import com.vodyanchuk.coursework.model.TaxRateUnderSimplifiedSystem;
import com.vodyanchuk.coursework.model.TemporaryTaxRateUnderSimplifiedSystem;

import java.util.List;

public interface TaxRateUnderSimplifiedSystemService {
    TaxRateUnderSimplifiedSystem save(TaxRateUnderSimplifiedSystem taxRateUnderSimplifiedSystem);
    TaxRateUnderSimplifiedSystem update(Long id, TaxRateUnderSimplifiedSystem taxRateUnderSimplifiedSystem);
    void delete(Long id);
    TaxRateUnderSimplifiedSystem findById(Long id);
    List<TaxRateUnderSimplifiedSystem> findAll();
    TaxRateUnderSimplifiedSystem findByCriteria(String criteria);

    TemporaryTaxRateUnderSimplifiedSystem calculateRate(TemporaryTaxRateUnderSimplifiedSystem requiredRate);
}
