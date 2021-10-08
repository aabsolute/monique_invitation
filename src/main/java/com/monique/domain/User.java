package com.monique.domain;

import com.monique.common.enums.RoleType;
import com.monique.user.dto.UserDTO;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder(builderMethodName = "UserBuilder")
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private long id;

    @Column(length = 32, nullable = false, unique=true)
    private String email;

    @Column(length = 32, nullable = false)
    private String userName;

    @Column(length = 128, nullable = false)
    private String password;

    @Column(length = 128)
    private String photo;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Column(length = 4)
    private String luckyNumber;

    public static UserBuilder builder(UserDTO user) {
        return UserBuilder()
                .id(user.getId())
                .email(user.getEmail())
                .userName(user.getUserName())
                .password(user.getPassword())
                .photo(user.getPhoto())
                .role(user.getRole())
                .luckyNumber(user.getLuckyNumber());

    }

    //for repository without password search
    public User(Long id, String email, String userName, RoleType role, String luckyNumber)
    {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.role = role;
        this.luckyNumber = luckyNumber;
    }

}
