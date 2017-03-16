package com.picanco.menuboard.repository.mybatis.typehandler;

import com.picanco.menuboard.domain.MenuItemIdentifier;

public class MenuItemIdentifierTypeHandler extends AbstractIntegerWrapperTypeHandler<MenuItemIdentifier> {
    public MenuItemIdentifierTypeHandler() {
        super(MenuItemIdentifier::create);
    }
}
