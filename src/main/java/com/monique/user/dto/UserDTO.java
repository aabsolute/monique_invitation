package com.monique.user.dto;

import com.monique.common.enums.RoleType;
import com.monique.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class UserDTO {

    private long id;

    private String userName;

    private String password;

    private String passwordCheck;

    private String email;

    private String photo;

    private RoleType role;
}
