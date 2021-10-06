package com.monique.admin.controller;

import com.monique.domain.User;
import com.monique.user.service.UserService;
import com.monique.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        List<UserDTO> userList = userService.getAllUserListExceptPassword();

        model.addAttribute("userList", userList);

        return "admin/user/user-list";
    }

    @GetMapping("user-manager")
    public String getAllUserManager(Model model, @RequestParam(value = "page", defaultValue = "0") int page){

        Page<User> userList = userService.getAllUserWithPaging(page);

        model.addAttribute("pages", userList );
        model.addAttribute("maxPage", 5);

        return "admin/user/user-list";
    }


}
