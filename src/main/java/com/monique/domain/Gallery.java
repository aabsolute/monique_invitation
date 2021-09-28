package com.monique.domain;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Getter
@DynamicUpdate
@Table(name = "Gallery")
@Entity
public class Gallery extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 32, nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String origFileName;

    @Column(nullable = false)
    private String filePath;

    private Long fileSize;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int likes;

    @OneToMany(mappedBy = "gallery", fetch = FetchType.EAGER) // mappedBy is not has relation we are inverse foreign key
    private List<GalleryReply> galleryReply; //

    @Builder
    public Gallery(String origFileName, String filePath, Long fileSize){
        this.origFileName = origFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
}
