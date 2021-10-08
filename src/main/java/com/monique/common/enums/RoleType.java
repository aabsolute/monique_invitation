package com.monique.common.enums;

import lombok.Getter;

import java.util.EnumSet;

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
