package com.monique.main.dto;

import com.monique.common.enums.LangType;
import com.monique.common.enums.RoleType;
import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;

@Getter
@Builder
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@ToString
public class UserSession implements Serializable{

    private static final long serialVersionUID = 1L;

    private String email;

    private RoleType role;

    private LangType language;


}
