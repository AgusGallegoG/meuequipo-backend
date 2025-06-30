package com.web.meuequipo.core.image;


import com.web.meuequipo.core.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "Image")
public class Image extends AuditableEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_name", nullable = false)
    private String originalName;

    @Column(name = "stored_name", nullable = false)
    private String storedName;

    @Column(name = "relative_path", nullable = false)
    private String relativePath;


}
