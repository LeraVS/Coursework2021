package com.vodyanchuk.coursework.model;

import com.vodyanchuk.coursework.model.enums.TypeOfTax;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "typeoftax")
    @Enumerated(EnumType.STRING)
    private TypeOfTax typeOfTax;
    @Column(name = "tax")
    private double tax;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idclient")
    private Client client;

}
