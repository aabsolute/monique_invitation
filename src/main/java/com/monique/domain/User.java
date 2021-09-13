package com.monique.domain;

import com.monique.common.enums.RoleType;
import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@DynamicUpdate
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 32, nullable = false, unique=true)
    private String email;

    @Column(length = 32, nullable = false)
    private String userName;

    @Column(length = 128, nullable = false)
    private String password;

    @Column(length = 128, nullable = false)
    private String photo;

    @Enumerated(EnumType.STRING)
    private RoleType role;
}
