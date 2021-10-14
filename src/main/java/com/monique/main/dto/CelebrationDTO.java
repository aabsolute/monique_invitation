package com.monique.main.dto;

import com.monique.domain.User;
import lombok.Data;

import javax.persistence.*;

@Data
public class CelebrationDTO {

    private long id;

    private String comment;

    private String name;

    private String passkey;

    private String delete_yn;

    private int totalComment;


}
