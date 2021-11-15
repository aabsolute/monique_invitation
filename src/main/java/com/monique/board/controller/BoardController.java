package com.monique.board.controller;

import com.monique.board.service.BoardService;
import com.monique.domain.Board;
import com.monique.domain.Gallery;
import com.monique.gallery.service.GalleryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {


    private final BoardService boardService;

    @GetMapping("blog")
    public String getBlog(Model model, @RequestParam(value = "page", defaultValue = "0") int page){

        Page<Board> galleryList = boardService.getAllBoardWithPaging(page);
        model.addAttribute("paging", galleryList );
        return "gallery/gallery";
    }
}
