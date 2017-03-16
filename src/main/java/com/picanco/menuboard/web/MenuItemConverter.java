package com.picanco.menuboard.web;

import com.picanco.menuboard.domain.MenuItemIdentifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MenuItemConverter implements Converter<String, MenuItemIdentifier> {

    @Override
    public MenuItemIdentifier convert(String s) {
        return MenuItemIdentifier.create(Integer.parseInt(s));
    }
}
