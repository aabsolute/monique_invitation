package com.monique.user.controller;

import com.monique.main.dto.UserSession;
import com.monique.user.service.UserService;
import com.monique.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String postSignUp(Model model, @ModelAttribute UserDTO userDTO, HttpServletRequest request, Locale locale){

        if(userDTO != null)
            userService.postUser(userDTO);

        HttpSession hss =  request.getSession();
        UserSession uss = UserSession.builder(userDTO, locale).build();
        hss.setAttribute("userSession", uss);

        log.debug(" @post SIGN-UP");
        return "redirect:/";
    }

    @GetMapping("/sign-out")
    public String logOut(HttpSession session){
        log.debug("get SIGN-OUT");

        UserSession user = (UserSession)session.getAttribute("userSession");

        if(user!= null)
        {
            session.removeAttribute("userSession");
            session.invalidate();
        }
        return "redirect:/";
    }


}
