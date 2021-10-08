package com.monique.user.dto;

import com.monique.common.enums.LangType;
import com.monique.common.enums.RoleType;
import com.monique.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserDTO {


    private long id;

    @NotBlank
    private String userName;
    @NotBlank
    private String password;

    private String passwordCheck;
    @NotBlank
    private String email;

    private String photo;

    private RoleType role;

    @Max(2)
    private String luckyNumber;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;
}
