package com.monique.main.controller;

import com.monique.common.enums.LangType;
import com.monique.common.enums.RoleType;
import com.monique.main.dto.UserSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

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

    @Value("wedding.date")
    private String strWeddingDate;

    @Value("wedding.dateTime")
    private String strWeddingDateTime;

    @GetMapping("/")
    public String getMain(Model model, HttpServletRequest request, HttpServletResponse response){

//        Cookie[] myCookies = request.getCookies();
//        for(int i = 0; i < myCookies.length; i++) {
//
//        }
//        Cookie myCookie = new Cookie("userInfo", null);
//        myCookie.setMaxAge(0); // 쿠키의 expiration 타임을 0으로 하여 없앤다.
//        myCookie.setPath("/"); // 모든 경로에서 삭제 됬음을 알린다.    response.addCookie(myCookie);
//        response.addCookie(myCookie);

        log.debug(messageSource.getMessage("lable.main.list.gallery", null, Locale.getDefault()));
        HttpSession session = request.getSession();
        //Test
        UserSession user = UserSession.builder().email("temp@maver.com").role(RoleType.ADMIN).language(LangType.KO).build();
        session.setAttribute("userSession",user);

        String[] transDateTime = strWeddingDateTime.split("", 2);

        LocalDateTime weddingDateTime = LocalDateTime.of(Integer.parseInt(transDateTime[transDateTime.length-transDateTime.length]
                        +transDateTime[transDateTime.length-transDateTime.length+1]),
                Integer.parseInt(transDateTime[transDateTime.length-transDateTime.length+2]),
                Integer.parseInt(transDateTime[transDateTime.length-transDateTime.length+3]),
                Integer.parseInt(transDateTime[transDateTime.length-transDateTime.length+4]),
                Integer.parseInt(transDateTime[transDateTime.length-transDateTime.length+5]));

        model.addAttribute("weddingDate",strWeddingDate);
        model.addAttribute("weddingDateTime",weddingDateTime);
        return "main";
    }

    @GetMapping("/our-story")
    public void getLove_Story(Model model){

        String[] transDateTime = strWeddingDateTime.split("", 2);

        LocalDateTime weddingDateTime = LocalDateTime.of(Integer.parseInt(transDateTime[transDateTime.length-transDateTime.length]
                        +transDateTime[transDateTime.length-transDateTime.length+1]),
                Integer.parseInt(transDateTime[transDateTime.length-transDateTime.length+2]),
                Integer.parseInt(transDateTime[transDateTime.length-transDateTime.length+3]),
                Integer.parseInt(transDateTime[transDateTime.length-transDateTime.length+4]),
                Integer.parseInt(transDateTime[transDateTime.length-transDateTime.length+5]));

        model.addAttribute("weddingDate",strWeddingDate);
        model.addAttribute("weddingDateTime",weddingDateTime);
    }
}
