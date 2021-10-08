package com.monique.gallery.dto;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class GalleryDTO {

    private int id;

    private String fileName;

    private String originalFileName;

    private String comment;

    private String filePath;

    private Long fileSize;

    private int likes;
}
