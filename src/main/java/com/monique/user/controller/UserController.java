package com.monique.user.controller;

import com.monique.domain.User;
import com.monique.main.dto.UserSession;
import com.monique.user.UserService;
import com.monique.user.dto.UserDTO;
import com.monique.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/sign-in")
    public String getSignIn(Model model, HttpServletRequest request){
        log.debug("get SIGN-UP");
        String referer = (String)request.getHeader("REFERER");

        return "user/sign-in";
    }

    @PostMapping("/sign-in")
    public String postSignIn(Model model, @ModelAttribute UserDTO userDTO, HttpServletRequest request, Locale locale) throws Exception{

        if(userDTO != null)
            userDTO = userService.findUser(userDTO);

        HttpSession hss = request.getSession();
        UserSession uss = UserSession.builder(userDTO, locale).build();
        hss.setAttribute("userSession", uss);

        return "redirect:/";
    }


    @GetMapping("/sign-up")
    public String getSignUp(Model model){
        log.debug("get SIGN-UP");
        model.addAttribute("registerUser", new UserDTO());

        return "user/sign-up";
    }

    @PostMapping("/sign-up")
    public String postSignUp(Model model, @ModelAttribute UserDTO userDTO){

        if(userDTO != null)
            userService.postUser(userDTO);

        log.debug(" @post SIGN-UP");
        return "redirect:/";
    }
}
