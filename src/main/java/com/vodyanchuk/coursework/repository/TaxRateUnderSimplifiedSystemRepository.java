package com.vodyanchuk.coursework.repository;

import com.vodyanchuk.coursework.model.TaxRateUnderSimplifiedSystem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxRateUnderSimplifiedSystemRepository extends JpaRepository<TaxRateUnderSimplifiedSystem, Long> {
    TaxRateUnderSimplifiedSystem findByCriteria(String criteria);
}
