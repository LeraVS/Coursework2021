package com.vodyanchuk.coursework.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "typeofbusiness")
@NoArgsConstructor
@Getter
@Setter
public class TypeOfBusiness {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtypeofbusiness")
    private Long idTypeOfBusiness;
    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "typeOfBusiness")
    private List<Client> clients;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "typeOfBusiness")
    private List<SingleTaxRate> singleTaxRates;
}
