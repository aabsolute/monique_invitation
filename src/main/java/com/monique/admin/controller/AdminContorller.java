package com.monique.admin.controller;

import com.monique.user.UserService;
import com.monique.user.dto.UserDTO;
import com.monique.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/admin/*")
@Controller
public class AdminContorller {

    private final UserService userService;

    @GetMapping("main")
    public String getDashBoardmain(){

        return "admin/dashboard";
    }

    @GetMapping("user")
    public String getUserManager(Model model){

        List<UserDTO> userList = userService.getAllUserList();

        model.addAttribute("userList", userList);

        return "admin/user/user-list";
    }

}
