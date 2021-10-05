package com.monique.user.repository;

import com.monique.common.enums.RoleType;

public interface UserMappingExceptPassCode {

    Long getId();
    String getEmail();
    String getUserName();
    String getLuckyNumber();
    RoleType getRole();
}
