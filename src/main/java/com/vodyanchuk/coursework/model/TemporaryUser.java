package com.vodyanchuk.coursework.model;

import com.vodyanchuk.coursework.model.enums.TypeOfTax;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class TemporaryUser {
    @NotNull
    private String name;

    @NotNull
    @Size(min = 2)
    private String address;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")
    private String email;

    @NotNull
    @Size(min=6)
    private String password;

    @NotNull
    @Size(min = 1)
    private Long idTypeOfBusiness;

    private Long idTradeLocation;

    private Long idObjectType;

    private TypeOfTax typeOfTax;
}
