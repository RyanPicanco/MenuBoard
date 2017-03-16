package com.picanco.menuboard.exception;

import com.picanco.menuboard.domain.MenuItemIdentifier;

public class MenuItemNotExistsException extends RuntimeException {
    public MenuItemNotExistsException(MenuItemIdentifier identifier) {
        super("Menu Item Identifier: " + identifier + " does not exist.");
    }
}
