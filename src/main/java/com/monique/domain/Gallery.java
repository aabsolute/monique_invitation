package com.monique.domain;

import com.monique.admin.dto.GalleryDTO;
import com.monique.user.dto.UserDTO;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder(builderMethodName = "GalleryBuilder")
@Entity
public class Gallery extends BaseTimeEntity {

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

    public static Gallery.GalleryBuilder builder(GalleryDTO gallery) {
        return GalleryBuilder()
                .id(gallery.getId())
                .fileName(gallery.getFileName())
                .origFileName(gallery.getOrigFileName())
                .filePath(gallery.getFilePath())
                .fileSize(gallery.getFileSize())
                .likes(gallery.getLikes());
    }
}