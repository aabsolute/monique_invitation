package com.monique.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
public class UrlLocaleResolver implements LocaleResolver {

    private static final String URL_LOCALE_ATTRIBUTE_NAME = "URL_LOCALE_ATTRIBUTE_NAME";

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        // ==> /SomeContextPath/en/...
        // ==> /SomeContextPath/fr/...
        // ==> /SomeContextPath/WEB-INF/pages/...
        String uri = request.getRequestURI();

        log.info("URI=" + uri);

        String prefixKo = request.getServletContext().getContextPath() + "/ko/";
        String prefixJa = request.getServletContext().getContextPath() + "/ja/";
        String prefixEn = request.getServletContext().getContextPath() + "/en/";

        Locale locale = null;

        // korean
        if (uri.startsWith(prefixKo)) {
            locale = Locale.KOREAN;
        }
        // japanese
        else if (uri.startsWith(prefixJa)) {
            locale = Locale.JAPANESE;
        }
        // English
        else if (uri.startsWith(prefixEn)) {
            locale = Locale.ENGLISH;
        }

        if (locale != null) {
            request.getSession().setAttribute(URL_LOCALE_ATTRIBUTE_NAME, locale);
        }
        if (locale == null) {
            locale = (Locale) request.getSession().getAttribute(URL_LOCALE_ATTRIBUTE_NAME);
            if (locale == null) {
                locale = Locale.KOREAN;
            }
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
