package com.monique.gallery.service;


import com.monique.common.enums.CommonCode;
import com.monique.domain.Gallery;
import com.monique.gallery.dto.GalleryDTO;
import com.monique.gallery.repository.GalleryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class GalleryService {

    private final GalleryRepository galleryRepo;

    private final int PAGE_POST_COUNT= 10;

    @Autowired
    ModelMapper modelMapper;

    // 1. admin galleryList -> All gallery with paging order by createdDATE
    @Transactional(readOnly = true)
    public Page<Gallery> getAllGalleryWithPaging(int pageNum)
    {
        return galleryRepo.findAll(PageRequest.of(pageNum==0? 0:pageNum-1, CommonCode.PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
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

}
