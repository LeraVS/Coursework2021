package com.vodyanchuk.coursework.repository;

import com.vodyanchuk.coursework.model.CalculationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalculationHistoryRepository extends JpaRepository<CalculationHistory, Long> {
    List<CalculationHistory> findByClientIdClient(Long id);
}
