package com.picanco.menuboard.domain;


import com.fasterxml.jackson.annotation.JsonValue;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractWrapper<T> {

    @NotNull
    private final T value;

    public AbstractWrapper(@NotNull T value) {
        this.value = value;
    }

    @NotNull
    @JsonValue
    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        return o != null && o instanceof AbstractWrapper && ((AbstractWrapper) o).getValue().equals(getValue());
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }}
