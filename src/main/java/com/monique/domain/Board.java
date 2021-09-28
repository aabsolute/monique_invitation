package com.monique.domain;

import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Getter
@DynamicUpdate
@Entity
public class Board extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 32, nullable = false)
    private String title;

    @Lob
    private String content;

    @ColumnDefault("0")
    private int count;

    @ManyToOne(fetch = FetchType.EAGER) // many board to one user
    @JoinColumn(name = "userId")
    private User user; //db는 관계형이지만 자바에서는 object를 사용할 수 있다

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // mappedBy is not has relation we are inverse foreign key
    private List<BoardReply> boardReply; //

}
