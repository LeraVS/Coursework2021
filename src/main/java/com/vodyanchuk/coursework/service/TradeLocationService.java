package com.vodyanchuk.coursework.service;

import com.vodyanchuk.coursework.model.TradeLocation;
import com.vodyanchuk.coursework.model.TypeOfBusiness;

import java.util.List;

public interface TradeLocationService {
    TradeLocation save(TradeLocation tradeLocation);
    TradeLocation update(Long id, TradeLocation tradeLocation);
    void delete(Long id);
    TradeLocation findById(Long id);
    List<TradeLocation> findAll();
}
