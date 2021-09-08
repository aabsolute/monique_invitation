package com.monique.domain;

import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@DynamicUpdate
@Entity
public class GalleryReply extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name="galleryId")
    private Gallery gallery;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;
}
