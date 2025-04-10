package com.web.meuequipo.core.audit;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity {

    @CreatedDate
    @Column(updatable = false)
    protected LocalDateTime creadoEn;

    @LastModifiedDate
    protected LocalDateTime modificadoEn;

    @CreatedBy
    @Column(updatable = false)
    protected String creadoPor;

    @LastModifiedBy
    protected String modificadoPor;
}
