package com.example.commerce.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Member {
    @Id
    private String userId;

    private String password;
    private String userName;
    private String birth;
    private String phone;
    private LocalDateTime subscription;

    private boolean emailAuthYn;
    private String emailAuthKey;
}
