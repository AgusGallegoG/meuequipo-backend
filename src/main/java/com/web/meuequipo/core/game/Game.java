package com.web.meuequipo.core.game;

import com.web.meuequipo.core.audit.AuditableEntity;
import com.web.meuequipo.core.category.Category;
import com.web.meuequipo.core.rival.Rival;
import com.web.meuequipo.core.season.Season;
import com.web.meuequipo.core.squad.Squad;
import com.web.meuequipo.core.team.Team;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@Entity
@EqualsAndHashCode(callSuper = false)
@Data
@Table(name = "Game")
public class Game extends AuditableEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "rival_id", nullable = false)
    private Rival rival;

    @Column(name = "is_local", nullable = false)
    private Boolean isLocal;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    @Column(name = "local_points")
    private Integer localPoints;

    @Column(name = "visitor_points")
    private Integer visitorPoints;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "game_state", nullable = false)
    private String gameState;

    @OneToOne(mappedBy = "game") // Siempre se carga al recuperar un game
    private Squad squad;
}
