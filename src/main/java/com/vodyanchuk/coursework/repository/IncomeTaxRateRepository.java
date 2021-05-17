package com.vodyanchuk.coursework.repository;

import com.vodyanchuk.coursework.model.IncomeTaxRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeTaxRateRepository extends JpaRepository<IncomeTaxRate, Long> {

    IncomeTaxRate findByCriteria(String criteria);
}
