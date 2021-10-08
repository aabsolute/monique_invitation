package com.monique.admin.dto;

import lombok.Data;

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
