package com.vodyanchuk.coursework.model;

import com.vodyanchuk.coursework.model.enums.Role;
import com.vodyanchuk.coursework.model.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "authorizationmanager")
@NoArgsConstructor
@Getter
@Setter
public class AuthorizationManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idauthorizationmanager")
    private Long idAuthorizationManager;
    @Column(name = "login")
    private String username;
    @Column(name = "password")
    private String password;

    @Transient
    private String repeatPassword;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idclient", referencedColumnName = "idclient")
    private Client client;
}
