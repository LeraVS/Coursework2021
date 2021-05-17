package com.vodyanchuk.coursework.model;


import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class TemporaryTaxRateUnderSimplifiedSystem {

    @NotNull
    @Size(min = 1)
    private double revenue;

    private double nonOperatingRevenue;

    private double cost;

    private double tax;

    @NotNull
    private boolean payingVat;
}
