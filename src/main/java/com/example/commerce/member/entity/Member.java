package com.example.commerce.member.entity;


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
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime subscription;

    private boolean emailAuthYn;
    private String emailAuthKey;
}
