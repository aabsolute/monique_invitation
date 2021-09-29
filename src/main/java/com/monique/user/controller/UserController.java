package com.monique.user.controller;

import com.monique.domain.User;
import com.monique.user.UserService;
import com.monique.user.dto.UserDTO;
import com.monique.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/sign-in")
    public String getSignIn(Model model){

        return "user/sign-in";
    }

    @PostMapping("/sign-in")
    public String postSignIn(Model model){

        return "user/sign-in";
    }


    @GetMapping("/sign-up")
    public String getSignUp(Model model){
        log.debug("get SIGN-UP");
        model.addAttribute("registerUser", new UserDTO());

        return "user/sign-up";
    }

    @PostMapping("/sign-up")
    public String postSignUp(Model model, @ModelAttribute UserDTO userDTO){

        User user = User.builder(userDTO).build();

        if(user != null)
            userService.registUser(user);

        log.debug(" @post SIGN-UP");
        return "/";
    }
}
