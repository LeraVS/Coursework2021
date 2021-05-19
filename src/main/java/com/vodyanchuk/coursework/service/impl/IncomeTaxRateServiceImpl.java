package com.vodyanchuk.coursework.service.impl;

import com.vodyanchuk.coursework.exception.ResourceNotFoundException;
import com.vodyanchuk.coursework.model.IncomeTaxRate;
import com.vodyanchuk.coursework.model.TemporaryIncomeTaxRate;
import com.vodyanchuk.coursework.repository.IncomeTaxRateRepository;
import com.vodyanchuk.coursework.service.IncomeTaxRateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeTaxRateServiceImpl implements IncomeTaxRateService {
    private final IncomeTaxRateRepository incomeTaxRateRepository;

    public IncomeTaxRateServiceImpl(IncomeTaxRateRepository incomeTaxRateRepository) {
        this.incomeTaxRateRepository = incomeTaxRateRepository;
    }

    @Override
    public IncomeTaxRate save(IncomeTaxRate incomeTaxRate) {
        return incomeTaxRateRepository.save(incomeTaxRate);
    }

    @Override
    public IncomeTaxRate update(Long id, IncomeTaxRate incomeTaxRate) {
        return incomeTaxRateRepository.findById(id).map(tax -> {
            tax.setCriteria(incomeTaxRate.getCriteria());
            tax.setPercent(incomeTaxRate.getPercent());
            return incomeTaxRateRepository.save(tax);
        }).orElseThrow(() -> new ResourceNotFoundException("Ставки не найдены"));
    }

    @Override
    public void delete(Long id) {
        incomeTaxRateRepository.deleteById(id);
    }

    @Override
    public IncomeTaxRate findById(Long id) {
        return incomeTaxRateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ставка не найдена"));
    }

    @Override
    public List<IncomeTaxRate> findAll() {
        return incomeTaxRateRepository.findAll();
    }

    @Override
    public IncomeTaxRate findByCriteria(String criteria) {
        return incomeTaxRateRepository.findByCriteria(criteria);
    }

    @Override
    public TemporaryIncomeTaxRate calculateRate(TemporaryIncomeTaxRate requiredRate) {
        double percent = incomeTaxRateRepository.findByCriteria("для ИП").getPercent();

        requiredRate.setTax((requiredRate.getRevenue() + requiredRate.getNonOperatingRevenue())*percent/100);

        return requiredRate;
    }
}
