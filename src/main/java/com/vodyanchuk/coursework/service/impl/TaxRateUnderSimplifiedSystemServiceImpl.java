package com.vodyanchuk.coursework.service.impl;

import com.vodyanchuk.coursework.exception.ResourceNotFoundException;
import com.vodyanchuk.coursework.model.TaxRateUnderSimplifiedSystem;
import com.vodyanchuk.coursework.model.TemporaryTaxRateUnderSimplifiedSystem;
import com.vodyanchuk.coursework.repository.TaxRateUnderSimplifiedSystemRepository;
import com.vodyanchuk.coursework.service.TaxRateUnderSimplifiedSystemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxRateUnderSimplifiedSystemServiceImpl implements TaxRateUnderSimplifiedSystemService {
    private final TaxRateUnderSimplifiedSystemRepository taxRateUnderSimplifiedSystemRepository;

    public TaxRateUnderSimplifiedSystemServiceImpl(TaxRateUnderSimplifiedSystemRepository taxRateUnderSimplifiedSystemRepository) {
        this.taxRateUnderSimplifiedSystemRepository = taxRateUnderSimplifiedSystemRepository;
    }

    @Override
    public TaxRateUnderSimplifiedSystem save(TaxRateUnderSimplifiedSystem taxRateUnderSimplifiedSystem) {
        return taxRateUnderSimplifiedSystemRepository.save(taxRateUnderSimplifiedSystem);
    }

    @Override
    public TaxRateUnderSimplifiedSystem update(Long id, TaxRateUnderSimplifiedSystem taxRateUnderSimplifiedSystem) {
        return null;  //TODO
    }

    @Override
    public void delete(Long id) {
        taxRateUnderSimplifiedSystemRepository.deleteById(id);
    }

    @Override
    public TaxRateUnderSimplifiedSystem findById(Long id) {
        return taxRateUnderSimplifiedSystemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ставка не найдена"));
    }

    @Override
    public List<TaxRateUnderSimplifiedSystem> findAll() {
        return taxRateUnderSimplifiedSystemRepository.findAll();
    }

    @Override
    public TaxRateUnderSimplifiedSystem findByCriteria(String criteria) {
        return taxRateUnderSimplifiedSystemRepository.findByCriteria(criteria);
    }

    @Override
    public TemporaryTaxRateUnderSimplifiedSystem calculateRate(TemporaryTaxRateUnderSimplifiedSystem requiredRate) {
        double percent1;
        double percent2 = taxRateUnderSimplifiedSystemRepository.findByCriteria("в отношении внереализационных доходов в виде безвозмездно полученных товаров (работ, услуг), имущественных прав, иных активов, суммы безвозмездно полученных денежных средств").getPercent();

        if (requiredRate.isPayingVat()) percent1 = taxRateUnderSimplifiedSystemRepository.findByCriteria("для ИП, уплачивающих НДС").getPercent();
        else percent1 = taxRateUnderSimplifiedSystemRepository.findByCriteria("для ИП, не уплачивающих НДС").getPercent();

        requiredRate.setTax((requiredRate.getRevenue() + requiredRate.getNonOperatingRevenue()-requiredRate.getCost())*percent1/100+requiredRate.getCost()*percent2/100);

        return requiredRate;
    }
}
