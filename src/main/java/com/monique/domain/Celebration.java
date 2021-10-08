package com.monique.domain;

import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.List;

@Getter
@DynamicInsert
@Entity
public class Celebration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 2048, nullable = false)
    private String content;

    @Column(length = 64, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER) // many board to one user
    @JoinColumn(name = "userId")
    private User user; //db는 관계형이지만 자바에서는 object를 사용할 수 있다


}
