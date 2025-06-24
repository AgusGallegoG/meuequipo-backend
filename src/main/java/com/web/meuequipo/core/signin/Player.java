package com.web.meuequipo.core.signin;

import com.web.meuequipo.core.category.Category;
import com.web.meuequipo.core.team.Team;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "Player")
@EqualsAndHashCode(callSuper = true)
public class Player extends Signin implements Serializable { // a traves de signin tiene auditableentity
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surnames", nullable = false)
    private String surnames;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "player_sex", nullable = false)
    private Long playerSex;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
}
