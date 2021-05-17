package com.vodyanchuk.coursework.model;

import com.vodyanchuk.coursework.model.enums.TypeOfTax;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "client")
@NoArgsConstructor
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idclient")
    private Long idClient;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "email")
    private String email;
    @Column(name = "typeoftax")
    @Enumerated(EnumType.STRING)
    private TypeOfTax typeOfTax;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtypeofbusiness")
    private TypeOfBusiness typeOfBusiness;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtradelocation")
    private TradeLocation tradeLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idobjecttype")
    private ObjectType objectType;

    @OneToOne(mappedBy = "client")
    private AuthorizationManager authorizationManager;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "client")
    private List<CalculationHistory> calculationHistories;

    public boolean hasTypeOfTax() {
        return typeOfTax != null;
    }

    public boolean isTypeSingleTax() {
        return typeOfTax == TypeOfTax.SINGLETAX;
    }

    public boolean isTypeIncomeTax() {
        return  typeOfTax == TypeOfTax.INCOMETAX;
    }

    public boolean isTypeTaxRateUnderSimplifiedSystem() {
        return typeOfTax == TypeOfTax.TAXUNDERSIMPLIFIEDSYSTEM;
    }
}
// name - название колонки client