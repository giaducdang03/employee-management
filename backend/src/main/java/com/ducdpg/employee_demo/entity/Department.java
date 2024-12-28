package com.ducdpg.employee_demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
@Table(name = "department")
@EntityListeners(AuditingEntityListener.class)
public class Department {

    @Column(name = "name", length = 150, nullable = false, unique = true)
    public String name;

    @Column(name = "description", length = 250, nullable = false, unique = true)
    public String description;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Employee> employee;

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    public String id;

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    public LocalDateTime createdDate;

    @LastModifiedBy
    @Column(name = "updated_date", updatable = false)
    public LocalDateTime updatedDate;

    @Column(name = "is_delete", nullable = false)
    public boolean isDelete;
}
