package com.ducdpg.employee_demo.models.account;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountModel {

    private String email;

    private String fullName;

    private String phoneNumber;

    public String id;

    public LocalDateTime createdDate;

    public LocalDateTime updatedDate;

    public boolean isDelete;
}
