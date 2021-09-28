package com.monique.domain;

import com.monique.common.enums.RoleType;
import com.monique.user.dto.UserDTO;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder(builderMethodName = "UserBuilder")
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

    @Column(length = 128)
    private String photo;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Column(length = 4)
    private String luckyNumber;

    public static UserBuilder builder(UserDTO userDTO) {
        return UserBuilder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .userName(userDTO.getUserName())
                .photo(userDTO.getPhoto())
                .role(userDTO.getRole())
                .luckyNumber(userDTO.getLuckyNumber());
    }

}
