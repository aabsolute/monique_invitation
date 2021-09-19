package com.monique.common.config;

import lombok.extern.slf4j.Slf4j;
import net.rakugakibox.util.YamlResourceBundle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.Assert;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@Slf4j
@Configuration
public class MessageConfig implements WebMvcConfigurer {

        @Value("${spring.messages.basename}")
        private String[] basefilenames;

        @Value("${spring.messages.encoding}")
        private String encoding;

        @Bean
        public SessionLocaleResolver localeResolver() {
//            CookieLocaleResolver localeResolver = new CookieLocaleResolver();
//            localeResolver.setDefaultLocale(Locale.KOREAN);
//            localeResolver.setCookieName("MONIQUE-LANG");
//            localeResolver.setCookieMaxAge(0);
//            localeResolver.setCookiePath("/");
            return new SessionLocaleResolver();
        }

        @Bean
        public LocaleChangeInterceptor localeChangeInterceptor() {
            LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
            lci.setParamName("lang");
            return lci;
        }

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(localeChangeInterceptor());
        }

        @Bean("messageSource")
        public MessageSource messageSource() {
            YamlMessageSource ms = new YamlMessageSource();
            if (basefilenames != null) {
                for (int i = 0; i < basefilenames.length; i++) {
                    String basename = basefilenames[i];
                    Assert.hasText(basename, "Basename must not be empty");
                    this.basefilenames[i] = basename.trim();
                }
                ms.setBasenames(basefilenames);
            } else {
                this.basefilenames = new String[0];

            }
            ms.setBasename(basefilenames[0]);
            ms.setDefaultEncoding(encoding);
            ms.setAlwaysUseMessageFormat(true);
            ms.setUseCodeAsDefaultMessage(true);
            ms.setFallbackToSystemLocale(true);
            return ms;
        }

    }

    class YamlMessageSource extends ResourceBundleMessageSource {
        @Override
        protected ResourceBundle doGetBundle(String basename, Locale locale) throws MissingResourceException {
            return ResourceBundle.getBundle(basename, locale, YamlResourceBundle.Control.INSTANCE);
        }
    }