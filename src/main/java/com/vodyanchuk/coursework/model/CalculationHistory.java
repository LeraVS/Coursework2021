package com.vodyanchuk.coursework.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "calculationhistory")
@NoArgsConstructor
@Getter
@Setter
public class CalculationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcalculationhistory")
    private Long idCalculationHistory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idclient")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idsingletaxrate")
    private SingleTaxRate singleTaxRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idincometaxrate")
    private IncomeTaxRate incomeTaxRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtaxrateundersimplifiedsystem")
    private TaxRateUnderSimplifiedSystem taxRateUnderSimplifiedSystem;
}
