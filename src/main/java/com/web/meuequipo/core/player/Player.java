package com.web.meuequipo.core.player;

import com.web.meuequipo.core.audit.AuditableEntity;
import com.web.meuequipo.core.category.Category;
import com.web.meuequipo.core.season.Season;
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
@EqualsAndHashCode(callSuper = false)
public class Player extends AuditableEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    private Season season;


    public String getPlayerCompleteName() {
        return this.name + " " + this.surnames;
    }
}
