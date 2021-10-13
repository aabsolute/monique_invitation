package com.monique.main.dto;

import com.monique.common.enums.LangType;
import com.monique.common.enums.RoleType;
import com.monique.user.dto.UserDTO;
import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.util.Locale;

@Getter
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Builder(builderMethodName = "UserSessionBuilder")
@ToString
public class UserSession implements Serializable{

    private static final long serialVersionUID = 1L;

    private String email;

    private String name;

    private RoleType role;

    private LangType language;

    public static UserSessionBuilder builder(UserDTO user, Locale locale) {
        return UserSessionBuilder()
                .email(user.getEmail())
                .name(user.getUserName())
                .role(user.getRole())
                .language(LangType.setLangTypeByLocale(locale));
    }

}
