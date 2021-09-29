package com.monique.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/admin/*")
@Controller
public class AdminContorller {


    @GetMapping("main")
    public String getDashBoardmain(){

        return "admin/dashboard";
    }

}
