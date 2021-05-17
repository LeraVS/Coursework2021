package com.vodyanchuk.coursework.model;


import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class TemporarySingleTaxRate {

    @NotNull
    @Size(min = 1)
    private Long idTypeOfBusiness;

    private Long idTradeLocation;

    private Long idObjectType;

    @NotNull
    @Size(min = 2)
    private String city;

    private int tax;
}
