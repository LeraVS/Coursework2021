package com.vodyanchuk.coursework.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "taxrateundersimplifiedsystem")
@NoArgsConstructor
@Getter
@Setter
public class TaxRateUnderSimplifiedSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtaxrateundersimplifiedsystem")
    private Long idTaxRateUnderSimplifiedSystem;
    @Column(name = "criteria")
    private String criteria;
    @Column(name = "percent")
    private Double percent;

}
