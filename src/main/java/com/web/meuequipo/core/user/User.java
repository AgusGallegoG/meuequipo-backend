package com.web.meuequipo.core.user;

import com.web.meuequipo.core.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "User")
public class User extends AuditableEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surnames")
    private String surnames;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "rol")
    private String rol = "ADMIN"; // Por ahora todos son administrators. Enum de roles

    @Column(name = "is_active", nullable = false)
    private boolean isActive;


//    public String getFullName() {
//        return name + " " + surnames;
//    }

    //Users are only administrators.
}
