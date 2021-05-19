package com.vodyanchuk.coursework;

import com.vodyanchuk.coursework.model.TaxRateUnderSimplifiedSystem;
import com.vodyanchuk.coursework.repository.TaxRateUnderSimplifiedSystemRepository;
import com.vodyanchuk.coursework.service.TaxRateUnderSimplifiedSystemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class TaxRateUnderSimplifiedSystemServiceTest {
    @Autowired
    private TaxRateUnderSimplifiedSystemService taxRateUnderSimplifiedSystemService;

    @MockBean
    private TaxRateUnderSimplifiedSystemRepository taxRateUnderSimplifiedSystemRepository;

    @Test
    @DisplayName("Test findById Success")
    void testFindById() {
        TaxRateUnderSimplifiedSystem taxRateUnderSimplifiedSystem = new TaxRateUnderSimplifiedSystem(1L, "для ИП, уплачивающих НДС", 3.0);
        doReturn(Optional.of(taxRateUnderSimplifiedSystem)).when(taxRateUnderSimplifiedSystemRepository).findById(1L);

        TaxRateUnderSimplifiedSystem returnedEntity = taxRateUnderSimplifiedSystemService.findById(1L);

        Assertions.assertTrue(returnedEntity != null, "TaxRateUnderSimplifiedSystem was not found");
        Assertions.assertSame(returnedEntity, taxRateUnderSimplifiedSystem, "TaxRateUnderSimplifiedSystem are not the same");
    }

    @Test
    @DisplayName("Test findAll Success")
    void testFindAll() {
        TaxRateUnderSimplifiedSystem taxRateUnderSimplifiedSystem = new TaxRateUnderSimplifiedSystem(1L, "для ИП, уплачивающих НДС", 3.0);
        TaxRateUnderSimplifiedSystem taxRateUnderSimplifiedSystem1 = new TaxRateUnderSimplifiedSystem(2L, "для ИП, не уплачивающих НДС", 5.0);
        TaxRateUnderSimplifiedSystem taxRateUnderSimplifiedSystem2 = new TaxRateUnderSimplifiedSystem(3L, "в отношении внереализационных доходов в виде безвозмездно полученных товаров (работ, услуг), имущественных прав, иных активов, суммы безвозмездно полученных денежных средств", 16.0);

        doReturn(Arrays.asList(taxRateUnderSimplifiedSystem,taxRateUnderSimplifiedSystem1,taxRateUnderSimplifiedSystem2)).when(taxRateUnderSimplifiedSystemRepository).findAll();

        List<TaxRateUnderSimplifiedSystem> returnedEntities = taxRateUnderSimplifiedSystemService.findAll();

        Assertions.assertEquals(3, returnedEntities.size(), "findAll should return 3 values");
    }
}
