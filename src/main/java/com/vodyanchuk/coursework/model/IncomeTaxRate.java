package com.vodyanchuk.coursework.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "incometaxrate")
@NoArgsConstructor
@Getter
@Setter
public class IncomeTaxRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idincometaxrate")
    private Long idIncomeTaxRate;
    @Column(name = "criteria")
    private String criteria;
    @Column(name = "percent")
    private Double percent;
}
