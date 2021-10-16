package com.monique.main.controller;

import com.google.gson.Gson;
import com.monique.domain.Celebration;
import com.monique.main.dto.CelebrationDTO;
import com.monique.main.service.CelebrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {

    private final LocaleResolver localeResolver;

    private final MessageSource messageSource;

    private final CelebrationService cbtService;

    @Autowired
    Gson gson;

    @Value("${cookie.expired-day}")
    private int cookieExpiredDay;

    @GetMapping("/")
    public String getMain(Model model, HttpServletRequest request, HttpServletResponse response,
                          @CookieValue(value = "MONIQUE-LANG", required = false) String moniqueLang,@RequestParam(value = "page", defaultValue = "0") int page
    ) {

        String cookieLang = null;
        if (moniqueLang != null && !moniqueLang.isEmpty()) {
            cookieLang = moniqueLang;
        }

        Page<Celebration> cbtList = cbtService.getAllCelebrationWithPaging(page);

        model.addAttribute("paging", cbtList );
        model.addAttribute("totalComment", cbtList.getTotalElements());
        model.addAttribute("cookieLang", cookieLang);
        return "main";
    }

    @GetMapping("/our-story")
    public String getLove_Story(Model model) {
        log.debug("OUR-STORY");
        return "story/our-story";
    }

    @ResponseBody
    @PostMapping(value = "/cbt-register")
    public String postCelebrationMessage(Model model, @ModelAttribute CelebrationDTO cbtDTO,HttpServletRequest request) {
        log.debug("cbtMsg");

        if(cbtDTO != null)
            cbtService.postCelebration(cbtDTO);
        else
            new Exception();

        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }


    private void createLanguageCookie2(String language, HttpServletResponse response)
    {
        Cookie myCookie = new Cookie("MONIQUE-LANG", language.toLowerCase());
        myCookie.setMaxAge(cookieExpiredDay*24*60*60);

        myCookie.setPath("/"); // 모든 경로에서 접근 가능 하도록 설정
        response.addCookie(myCookie);
    }


}
