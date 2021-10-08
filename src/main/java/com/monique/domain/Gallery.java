package com.monique.domain;

import com.monique.admin.dto.GalleryDTO;
import com.monique.user.dto.UserDTO;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder(builderMethodName = "GalleryBuilder")
@Entity
public class Gallery extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 32, nullable = false)
    private String fileName;

    @Column(length = 256, nullable = false)
    private String originalFileName;

    @Column(length = 256, nullable = false)
    private String filePath;

    @Column(nullable = false)
    private Long fileSize;

    @Column(length = 256)
    private String thumbImg;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int likes;

    @OneToMany(mappedBy = "gallery", fetch = FetchType.EAGER) // mappedBy is not has relation we are inverse foreign key
    private List<GalleryReply> galleryReply; //

    public static GalleryBuilder builder(GalleryDTO gallery) {
        return GalleryBuilder()
                .id(gallery.getId())
                .fileName(gallery.getFileName())
                .originalFileName(gallery.getOrigFileName())
                .filePath(gallery.getFilePath())
                .fileSize(gallery.getFileSize())
                .thumbImg(gallery.getThumbImg())
                .likes(gallery.getLikes());
    }

    @PrePersist
    public void prePersist(){
        this.likes=0;
    }

}