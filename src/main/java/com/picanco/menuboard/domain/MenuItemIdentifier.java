package com.picanco.menuboard.domain;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MenuItemIdentifier extends IntegerWrapper {

    private static final Map<Integer, MenuItemIdentifier> INSTANCES = new HashMap<>();

    public static MenuItemIdentifier create(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Value (" + value + ") for a MenuItemIdentifier must be > 0");
        }

        final MenuItemIdentifier identifier;
        if (INSTANCES.containsKey(value)) {
            identifier = INSTANCES.get(value);
        } else {
            identifier = new MenuItemIdentifier(value);
            INSTANCES.put(value, identifier);
        }

        return identifier;
    }

    private MenuItemIdentifier(@NotNull Integer value) {
        super(value);
    }

}
