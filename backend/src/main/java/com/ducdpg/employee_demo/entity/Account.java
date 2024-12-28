package com.ducdpg.employee_demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "account")
@EntityListeners(AuditingEntityListener.class)
public class Account {

    @Column(name = "email", length = 150, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 500, nullable = false)
    private String password;

    @Column(name = "full_name", length = 150, nullable = false)
    private String fullName;

    @Column(name = "phone_number", length = 10, nullable = false)
    private String phoneNumber;

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
