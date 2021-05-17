package com.vodyanchuk.coursework.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tradelocation")
@NoArgsConstructor
@Getter
@Setter
public class TradeLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtradelocation")
    private Long idTradeLocation;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tradeLocation")
    private List<Client> clients;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tradeLocation")
    private List<SingleTaxRate> singleTaxRates;
}
