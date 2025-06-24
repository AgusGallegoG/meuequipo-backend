package com.web.meuequipo.core.rival;

import com.web.meuequipo.core.audit.AuditableEntity;
import com.web.meuequipo.core.category.Category;
import com.web.meuequipo.core.image.Image;
import com.web.meuequipo.core.season.Season;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "Rival")
public class Rival extends AuditableEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "responsible")
    private String responsible;

    @Column(name = "tlf", nullable = false)
    private String tlf;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logo_id")
    private Image logo;

    @ManyToMany
    @JoinTable(
            name = "rival_category"
            , joinColumns = @JoinColumn(name = "rival_id")
            , inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;
}
