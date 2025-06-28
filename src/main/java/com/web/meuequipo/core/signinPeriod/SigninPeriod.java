package com.web.meuequipo.core.signinPeriod;

import com.web.meuequipo.core.audit.AuditableEntity;
import com.web.meuequipo.core.season.Season;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "signin_period")
@Data
@EqualsAndHashCode(callSuper = false)
public class SigninPeriod extends AuditableEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_init")
    private LocalDate dateInit;

    @Column(name = "date_end")
    private LocalDate dateEnd;

    @Column(name = "form_path")
    private String formPath;

    @Column(name = "downloads")
    private Integer downloads;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id", unique = true)
    private Season season;

    //Metodos para atributos calculados:

    public Boolean hasForm() {
        return formPath != null;
    }

    public Boolean isActive() {
        LocalDate now = LocalDate.now();
        boolean isAfterOrEqualInit = now.isAfter(dateInit) || now.isEqual(dateInit);
        boolean isBeforeOrEqualEnd = now.isBefore(dateEnd) || now.isEqual(dateEnd);

        return isAfterOrEqualInit && isBeforeOrEqualEnd;
    }

}
