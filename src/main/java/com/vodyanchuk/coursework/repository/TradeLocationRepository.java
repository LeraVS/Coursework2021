package com.vodyanchuk.coursework.repository;

import com.vodyanchuk.coursework.model.TradeLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeLocationRepository extends JpaRepository<TradeLocation, Long> {
}
