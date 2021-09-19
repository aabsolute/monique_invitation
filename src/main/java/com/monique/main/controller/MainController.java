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
import java.time.LocalDateTime;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {

    private final LocaleChangeInterceptor lci;

    private final MessageSource messageSource;

    @GetMapping("/")
    public String getMain(Model model, HttpServletRequest request, HttpServletResponse response,
                          @CookieValue(value = "MONIQUE-LANG", required = false) String moniqueLang, @RequestParam(name = "lang", required = false) String paramLang) {

        //            user = UserSession.builder().email("").role(RoleType.ADMIN).language(LangType.JP).build();

        String cookieLang = null;
        if (moniqueLang != null && !moniqueLang.isEmpty()) {
            cookieLang = moniqueLang;
        }
        if(paramLang != null && !paramLang.isEmpty()){
            createLanguageCookie2(paramLang, response);
            cookieLang = paramLang;
        }

        log.debug(messageSource.getMessage("our-story.major.heading", null, Locale.KOREAN));

        model.addAttribute("cookieLang", cookieLang);
        return "main";
    }

    @GetMapping("/our-story")
    public void getLove_Story(Model model) {
        log.debug("OUR-STORY");
    }

    @GetMapping("/sign-up")
    public String getSignUp() {
        log.debug("SIGN-UP");

        return "/sign/sign-up";
    }

    @GetMapping("/sign-in")
    public String getSignIn() {
        log.debug("SIGN-IN");
        return "/sign/sign-in";
    }


    /* 작가 검색 팝업창 */
    @GetMapping("/language-popup")
    public String authorPopGET() throws Exception {

        log.info("authorPopGET.......");
        return "/pop-up/language-popup";
    }

    @PostMapping("/createCooKie")
    @ResponseBody()
    public String createLanguageCookie(@RequestParam(name = "lang") String language, HttpServletResponse response){

        Cookie myCookie = new Cookie("MONIQUE-LANG", language.toLowerCase());
        myCookie.setMaxAge(0);
        myCookie.setPath("/"); // 모든 경로에서 접근 가능 하도록 설정
        response.addCookie(myCookie);

        return language;
    }

    private void createLanguageCookie2(String language, HttpServletResponse response)
    {
        Cookie myCookie = new Cookie("MONIQUE-LANG", language.toLowerCase());
        myCookie.setMaxAge(0);
        myCookie.setPath("/"); // 모든 경로에서 접근 가능 하도록 설정
        response.addCookie(myCookie);
    }

}
