package com.monique.gallery.controller;

import com.monique.domain.Gallery;
import com.monique.gallery.service.GalleryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RequiredArgsConstructor
@Controller
public class GalleryController {

    private final GalleryService galleryService;

    @GetMapping("gallery")
    public String getGallery(Model model,@RequestParam(value = "page", defaultValue = "0") int page){

        Page<Gallery> galleryList = galleryService.getAllGalleryWithPaging(page);

        model.addAttribute("paging", galleryList );

        return "gallery/gallery";
    }


    @ResponseBody
    @PostMapping("gallery-like")
    public String postGalleryLike(String galleryId){



        return "gallery/gallery";
    }

}
