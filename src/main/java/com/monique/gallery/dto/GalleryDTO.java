package com.monique.gallery.dto;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
public class GalleryDTO {

    private int id;

    private String fileName;

    private String origFileName;

    private String filePath;

    private Long fileSize;

    private String thumbImg;

    private int likes;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;
}
