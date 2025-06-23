package com.web.meuequipo.core.audit;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity {

    @CreatedDate
    @Column(name = "created_date", updatable = false, nullable = false)
    protected LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    protected LocalDateTime lastModifiedDate;

    @CreatedBy
    @Column(name = "created_by", updatable = false, nullable = false)
    protected String createdBy;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    protected String lastModifiedBy;
}
