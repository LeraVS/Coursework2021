package com.vodyanchuk.coursework.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "singletaxrate")
@NoArgsConstructor
@Getter
@Setter
public class SingleTaxRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsingletaxrate")
    private Long idSingleTaxRate;
    @Column(name = "city")
    private String city;
    @Column(name = "tax")
    private int tax;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtypeofbusiness")
    private TypeOfBusiness typeOfBusiness;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtradelocation")
    private TradeLocation tradeLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idobjecttype")
    private ObjectType objectType;
}
