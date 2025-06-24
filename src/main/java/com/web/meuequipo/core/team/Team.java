package com.web.meuequipo.core.team;

import com.web.meuequipo.core.audit.AuditableEntity;
import com.web.meuequipo.core.category.Category;
import com.web.meuequipo.core.image.Image;
import com.web.meuequipo.core.season.Season;
import com.web.meuequipo.core.signin.Player;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Team")
@EqualsAndHashCode(callSuper = false)
public class Team extends AuditableEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "trainer", nullable = false)
    private String trainer;

    @Column(name = "trainer_contact")
    private String trainerContact;

    @Column(name = "sex", nullable = false)
    private Long sex;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_image_id")
    private Image teamImage;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;


    //Métodos para mantener la relacion bidireccional con Player donde Team no es el propietario:
    public void addPlayer(Player player) {
        players.add(player);
        player.setTeam(this);
    }

    public void removePlayer(Player player) {
        players.remove(player);
        player.setTeam(null);
    }

    public Integer countPlayers() {
        return players.size();
    }
}
