package com.picanco.menuboard.domain;

import org.jetbrains.annotations.NotNull;

public class ByteArrayProductImage implements ProductImage {

    @NotNull private final byte[] bytes;

    public ByteArrayProductImage(@NotNull byte[] bytes) {
        this.bytes = bytes;
    }

    @NotNull
    public byte[] getImageBytes() {
        return bytes;
    }


}
