package com.monique.user.controller;

import com.monique.common.enums.RoleType;
import com.monique.domain.User;
import com.monique.user.dto.UserDTO;
import com.monique.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/sign-in")
    public String getSignIn(Model model){

        return "user/sign-in";
    }


    @GetMapping("/sign-up")
    public String getSignUp(Model model){
        log.debug("get SIGN-UP");
        model.addAttribute("registerUser", new UserDTO());

//        EnumSet<RoleType> cards3 = EnumSet.complementOf(RoleType.A); //선택한거 제외하고 가져오기

        return "user/sign-up";
    }

    @PostMapping("/sign-up")
    public String postSignUp(Model model, @ModelAttribute UserDTO userDTO){

        User user = User.builder(userDTO).build();

        if(user != null)
            userRepository.save(user);

        log.debug(" @post SIGN-UP");
        return "/";
    }
}
