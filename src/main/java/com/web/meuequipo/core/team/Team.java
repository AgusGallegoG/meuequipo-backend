package com.web.meuequipo.core.team;

import com.web.meuequipo.core.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Team")
@EqualsAndHashCode(callSuper = false)

public class Team extends AuditableEntity implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_seq_generator")
    @SequenceGenerator(name = "team_seq_generator", sequenceName = "team_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "active")
    private boolean active;

    // will have players / trainers /
}
