package com.ducdpg.employee_demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    public String id;

    @CreatedDate
    @Column(name = "createdDate", nullable = false, updatable = false)
    public LocalDateTime createdDate;

    @LastModifiedBy
    @Column(name = "updatedDate", updatable = false)
    public LocalDateTime updatedDate;
}
