package com.monique.admin.controller;

import com.monique.common.enums.CommonCode;
import com.monique.domain.Gallery;
import com.monique.domain.User;
import com.monique.gallery.dto.GalleryDTO;
import com.monique.gallery.service.GalleryService;
import com.monique.user.dto.UserDTO;
import com.monique.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/admin/*")
@Controller
public class AdminContorller {

    private final UserService userService;

    private final GalleryService galleryService;

    @GetMapping("main")
    public String getDashBoardmain(){

        return "admin/dashboard";
    }

    @GetMapping("user-manager")
    public String getAllUserManager(Model model, @RequestParam(value = "page", defaultValue = "0") int page){

        Page<User> userList = userService.getAllUserWithPaging(page);

        model.addAttribute("paging", userList );

        return "admin/user/user-list";
    }

    @GetMapping("gallery-manager")
    public String getAllGalleryManager(Model model, @RequestParam(value = "page", defaultValue = "0") int page){

        Page<Gallery> galleryList = galleryService.getAllGalleryWithPaging(page);

        model.addAttribute("paging", galleryList );

        return "admin/gallery/gallery-list";
    }

    @GetMapping("gallery-update")
    public String getGalleryUpdate(Model model){

        return "admin/gallery/gallery-update";
    }

    @PostMapping("gallery-update")
    public String postGalleryUpdate(Model model, GalleryDTO galleryDTO){

        return "admin/gallery/gallery-update";
    }

}
