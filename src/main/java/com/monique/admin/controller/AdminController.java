package com.monique.admin.controller;

import com.monique.common.utils.FileUtils;
import com.monique.domain.Gallery;
import com.monique.domain.User;
import com.monique.gallery.dto.GalleryDTO;
import com.monique.gallery.service.GalleryService;
import com.monique.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/admin/*")
@Controller
public class AdminController {

    private final UserService userService;

    private final GalleryService galleryService;

    @Value("${files.directory}")
    private String imgDir;

    @GetMapping("main")
    public String getDashBoardmain() {

        return "admin/dashboard";
    }

    @GetMapping("user-manager")
    public String getAllUserManager(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {

        Page<User> userList = userService.getAllUserWithPaging(page);

        model.addAttribute("paging", userList);

        return "admin/user/user-list";
    }

    @GetMapping("gallery-manager")
    public String getAllGalleryManager(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {

        Page<Gallery> galleryList = galleryService.getAllGalleryWithPaging(page);

        model.addAttribute("paging", galleryList);

        return "admin/gallery/gallery-list";
    }

    @GetMapping("gallery-upload")
    public String getGalleryUpdate(Model model) {

        return "admin/gallery/gallery-upload";
    }

    @PostMapping("gallery-upload")
    public String postGalleryUpdate(Model model, GalleryDTO galleryDTO, MultipartFile file, HttpServletRequest req) throws Exception {

        String imgUploadPath = imgDir + "image";
        String ymdPath = FileUtils.calcPath(imgUploadPath);
        String fileName = null;

        if (file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
            fileName = FileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath);

            galleryDTO.setFileName(fileName);
            galleryDTO.setOrigFileName(file.getOriginalFilename());
            galleryDTO.setGalleryImg(File.separator +"image" + ymdPath + File.separator + fileName);
            galleryDTO.setThumbImg(
                    File.separator + "image" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
        } else {
            fileName = File.separator + "images" + File.separator + "none.png";

            galleryDTO.setGalleryImg(fileName);
            galleryDTO.setThumbImg(fileName);
        }


        galleryService.postGalleryInfo(galleryDTO);

        return "redirect:main";
    }

}
