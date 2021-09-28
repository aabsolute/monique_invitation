package com.monique.common.enums;

import lombok.Getter;

import javax.management.relation.Role;
import java.util.List;

@Getter
public enum RoleType {
    FRIEND("ROLE_FRIEND"),
    FAMILY("ROLE_FAMILY"),
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");


    final private String authority ;

    private RoleType( String authority ) {
        this.authority  = authority ;
    }

    public RoleType[] exceptAdmin(){
        RoleType[] AllRole = RoleType.values();
        RoleType[] role = new RoleType[3];
        for(int i = 0; i < AllRole.length; i++)
        {
            if(AllRole[i] != ADMIN)
                role[i] = AllRole[i];
        }

        return role;
    }
}
