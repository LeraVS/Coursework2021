package com.vodyanchuk.coursework.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ChangedTaxRatesUnderSimplifiedSystem {
    private List<TaxRateUnderSimplifiedSystem> taxRatesUnderSimplifiedSystem;
}
