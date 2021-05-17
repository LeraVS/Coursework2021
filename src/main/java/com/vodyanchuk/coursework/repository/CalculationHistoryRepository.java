package com.vodyanchuk.coursework.repository;

import com.vodyanchuk.coursework.model.CalculationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationHistoryRepository extends JpaRepository<CalculationHistory, Long> {
    CalculationHistory findByClientIdClient(Long id);
}
