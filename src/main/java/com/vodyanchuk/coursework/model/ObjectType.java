package com.vodyanchuk.coursework.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "objecttype")
@NoArgsConstructor
@Getter
@Setter
public class ObjectType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idobjecttype")
    private Long idObjectType;
    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "objectType")
    private List<Client> clients;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "objectType")
    private List<SingleTaxRate> singleTaxRates;
}
