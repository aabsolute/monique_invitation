package com.monique.admin.dto;

import lombok.Data;

@Data
public class GalleryDTO {

    private Long id;

    private String fileName;

    private String origFileName;

    private String filePath;

    private Long fileSize;

    private int likes;
}
