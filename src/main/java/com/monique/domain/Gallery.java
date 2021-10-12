package com.monique.domain;

import com.monique.gallery.dto.GalleryDTO;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(builderMethodName = "GalleryBuilder")
@Entity
public class Gallery extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="gallery_id", updatable = false)
    private int id;

    @Column(length = 64, nullable = false)
    private String fileName;

    @Column(length = 256, nullable = false)
    private String originalFileName;

    @Column(length = 256, nullable = false)
    private String galleryImg;

    @ColumnDefault("0")
    private Long fileSize;

    @Column(length = 256)
    private String thumbImg;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int likes;

    @Column(length = 256)
    private String imgDescription;

    @OneToMany(mappedBy = "gallery", fetch = FetchType.EAGER) // mappedBy is not has relation we are inverse foreign key
    private List<GalleryReply> galleryReply; //

    public static GalleryBuilder builder(GalleryDTO gallery) {
        return GalleryBuilder()
                .id(gallery.getId())
                .fileName(gallery.getFileName())
                .originalFileName(gallery.getOrigFileName())
                .galleryImg(gallery.getGalleryImg())
                .fileSize(gallery.getFileSize())
                .thumbImg(gallery.getThumbImg())
                .likes(gallery.getLikes())
                .imgDescription(gallery.getImgDescription());
    }

    @PrePersist
    public void prePersist(){
        this.likes=0;
    }

}