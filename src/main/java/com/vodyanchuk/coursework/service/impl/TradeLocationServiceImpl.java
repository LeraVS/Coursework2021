package com.vodyanchuk.coursework.service.impl;

import com.vodyanchuk.coursework.exception.ResourceNotFoundException;
import com.vodyanchuk.coursework.model.Client;
import com.vodyanchuk.coursework.model.TradeLocation;
import com.vodyanchuk.coursework.model.TypeOfBusiness;
import com.vodyanchuk.coursework.repository.TradeLocationRepository;
import com.vodyanchuk.coursework.repository.TypeOfBusinessRepository;
import com.vodyanchuk.coursework.service.TradeLocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeLocationServiceImpl implements TradeLocationService {

    private final TradeLocationRepository tradeLocationRepository;

    public TradeLocationServiceImpl(TradeLocationRepository tradeLocationRepository) {
        this.tradeLocationRepository = tradeLocationRepository;
    }

    @Override
    public TradeLocation save(TradeLocation tradeLocation) {
        return tradeLocationRepository.save(tradeLocation);
    }

    @Override
    public TradeLocation update(Long id, TradeLocation tradeLocation) {
        return tradeLocationRepository.findById(id).map(location -> {
            location.setName(tradeLocation.getName());
            if (tradeLocation.getClients() != null) {
                List<Client> clients = location.getClients();
                clients.clear();
                clients.addAll(tradeLocation.getClients());
            }
            return tradeLocationRepository.save(location);
        }).orElseThrow(() -> new ResourceNotFoundException("Место торговли не найдено"));
    }

    @Override
    public void delete(Long id) {
        tradeLocationRepository.deleteById(id);
    }

    @Override
    public TradeLocation findById(Long id) {
        return tradeLocationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Место торговли не найдено"));
    }

    @Override
    public List<TradeLocation> findAll() {
        return tradeLocationRepository.findAll();
    }
}
