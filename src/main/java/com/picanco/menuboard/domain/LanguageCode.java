package com.picanco.menuboard.domain;

public class LanguageCode extends StringWrapper {

    public static LanguageCode EN = new LanguageCode("en");

    public LanguageCode(String value) {
        super(value);
    }
}
