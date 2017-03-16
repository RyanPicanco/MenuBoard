package com.picanco.menuboard.domain;

import org.jetbrains.annotations.NotNull;

public class IntegerWrapper extends AbstractWrapper<Integer> implements Comparable<IntegerWrapper> {
    public IntegerWrapper(Integer value) {
        super(value);
    }

    @Override
    public int compareTo(@NotNull IntegerWrapper o) {
        return getValue().compareTo(o.getValue());
    }
}
