package com.monique.common.enums;

import lombok.Getter;

import javax.management.relation.Role;
import java.awt.*;
import java.util.EnumSet;
import java.util.List;

import static java.util.EnumSet.complementOf;

@Getter
public enum RoleType {
    FRIEND("ROLE_FRIEND"),
    FAMILY("ROLE_FAMILY"),
    USER("ROLE_USER"),
    ATTENDEE("ROLE_ATTENDEE"),
    ADMIN("ROLE_ADMIN");


    final private String authority ;

    private RoleType( String authority ) {
        this.authority  = authority ;
    }

    public static RoleType[] exceptAdmin(){
        EnumSet<RoleType> set = EnumSet.complementOf(EnumSet.of(RoleType.ADMIN, RoleType.ATTENDEE));
        return set.toArray(new RoleType[set.size()]);
    }
}
