package com.vodyanchuk.coursework.repository;

import com.vodyanchuk.coursework.model.SingleTaxRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingleTaxRateRepository extends JpaRepository<SingleTaxRate, Long> {

    SingleTaxRate findByTypeOfBusinessIdTypeOfBusinessAndTradeLocationIdTradeLocationAndObjectTypeIdObjectTypeAndCity(
            Long idTypeOfBusiness, Long idTradeLocation, Long idObjectType, String city);

}
