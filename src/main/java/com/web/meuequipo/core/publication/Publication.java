package com.web.meuequipo.core.publication;

import com.web.meuequipo.core.image.Image;
import com.web.meuequipo.core.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "Publication")
public class Publication extends AuditableEntity implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publication_seq_generator")
    @SequenceGenerator(name = "publication_seq_generator", sequenceName = "publication_seq", allocationSize = 1)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @Column(name = "creationDate")
    private Date creationDate;

    //Probably add user ?? to see who created it.
    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> image;
}
