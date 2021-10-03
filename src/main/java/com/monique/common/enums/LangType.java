package com.monique.common.enums;

import java.util.Locale;

public enum LangType {
    ko, ja, en;

    public static LangType setLangTypeByLocale(Locale locale){
        if(locale != null)
        {
            String lclLang = locale.getLanguage();
            switch (lclLang){
                case "ja": return LangType.ja;
                case "en": return LangType.en;
                default: return LangType.ko;
            }
        }

        return LangType.ko;
    }


}

