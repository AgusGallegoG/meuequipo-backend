package com.web.meuequipo.core.image;


import com.web.meuequipo.core.publication.Publication;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "Imagen")
public class Image implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_seq_generator")
    @SequenceGenerator(name = "image_seq_generator", sequenceName = "image_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;

    @Lob
    @Column(name = "uri")
    private String imageURI;

    // References
    @ManyToOne
    @JoinColumn(name = "publication_id", nullable = false)
    private Publication publication;
}
