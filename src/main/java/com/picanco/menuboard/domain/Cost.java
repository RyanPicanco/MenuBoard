package com.picanco.menuboard.domain;

import org.jetbrains.annotations.NotNull;

public class Cost extends AbstractWrapper<Float> {
    public Cost(@NotNull Float value) {
        super(value);
    }
}
