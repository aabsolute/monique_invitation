package com.monique.gallery.service;

import com.monique.admin.dto.GalleryDTO;
import com.monique.domain.Gallery;
import com.monique.gallery.repository.GalleryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class GalleryService {

    private final GalleryRepository galleryRepo;

    @Autowired
    ModelMapper modelMapper;

    // 1. admin galleryList -> All gallery with paging
    @Transactional(readOnly = true)
    public Page<Gallery> getAllGalleryWithPaging(int startAt)
    {
        Pageable pageable = PageRequest.of(startAt, 10);
        return galleryRepo.findAll(pageable);
    }

    // 2. gallery Info By One
    @Transactional(readOnly = true)
    public GalleryDTO getGalleryInfo(int id)
    {
        Gallery result = galleryRepo.findById(id).orElseThrow();
        return modelMapper.map(result, GalleryDTO.class);
    }

    //3. gallery register
    @Transactional
    public void postGalleryInfo(GalleryDTO galleryDTO)
    {
        galleryRepo.save(Gallery.builder(galleryDTO).build());
    }

    //4. update gallery
    @Transactional
    public void getGalleryInfo(GalleryDTO galleryDTO)
    {
        galleryRepo.save(Gallery.builder(galleryDTO).build());
    }
}
