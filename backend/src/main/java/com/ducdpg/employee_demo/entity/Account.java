package com.ducdpg.employee_demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "account")
@EntityListeners(AuditingEntityListener.class)
public class Account implements UserDetails {

    @Column(name = "email", length = 150, nullable = false, unique = true)
    public String email;

    @Column(name = "password", length = 500, nullable = false)
    public String password;

    @Column(name = "full_name", length = 150, nullable = false)
    public String fullName;

    @Column(name = "phone_number", length = 10, nullable = false)
    public String phoneNumber;

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    public String id;

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    public LocalDateTime createdDate;

    @LastModifiedBy
    @Column(name = "updated_date")
    public LocalDateTime updatedDate;

    @Column(name = "is_delete", nullable = false)
    public boolean isDelete;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !isDelete;
    }
}
