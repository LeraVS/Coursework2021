package com.vodyanchuk.coursework.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ChangedSingleTaxRates {
    private List<SingleTaxRate> singleTaxRates;
}
