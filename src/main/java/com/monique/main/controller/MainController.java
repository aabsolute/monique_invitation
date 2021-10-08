package com.monique.main.controller;

import com.monique.common.enums.LangType;
import com.monique.common.enums.RoleType;
import com.monique.main.dto.UserSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.startup.UserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {

    private final LocaleResolver localeResolver;

    private final MessageSource messageSource;

    @Value("${cookie.expired-day}")
    private int cookieExpiredDay;

    @GetMapping("/")
    public String getMain(Model model, HttpServletRequest request, HttpServletResponse response,
                          @CookieValue(value = "MONIQUE-LANG", required = false) String moniqueLang
    ) {

        String cookieLang = null;
        if (moniqueLang != null && !moniqueLang.isEmpty()) {
            cookieLang = moniqueLang;
        }
        model.addAttribute("cookieLang", cookieLang);
        return "main";
    }

    @GetMapping("/our-story")
    public String getLove_Story(Model model) {
        log.debug("OUR-STORY");
        return "story/our-story";
    }

    @ResponseBody
    @RequestMapping(value = "/celebrationMsg", method = RequestMethod.POST)
    public String postCelebrationMessage(Model model) {
        log.debug("celebrationMsg");

        return "main";
    }


    private void createLanguageCookie2(String language, HttpServletResponse response)
    {
        Cookie myCookie = new Cookie("MONIQUE-LANG", language.toLowerCase());
        myCookie.setMaxAge(cookieExpiredDay*24*60*60);

        myCookie.setPath("/"); // 모든 경로에서 접근 가능 하도록 설정
        response.addCookie(myCookie);
    }

    @GetMapping("/index")
    public void getIndex(Model model){
        log.info("get Index");
    }

}
