package com.web.meuequipo.core.signin;

import com.web.meuequipo.core.audit.AuditableEntity;
import com.web.meuequipo.core.season.Season;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Signin")
public abstract class Signin extends AuditableEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parent_name", nullable = false)
    private String parentName;

    @Column(name = "parent_surnames", nullable = false)
    private String parentSurnames;

    @Column(name = "mail", nullable = false)
    private String mail;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "signin_state", nullable = false)
    private Long signinState;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    public String getParentCompleteName() {
        return this.parentName + " " + this.parentSurnames;
    }
}
