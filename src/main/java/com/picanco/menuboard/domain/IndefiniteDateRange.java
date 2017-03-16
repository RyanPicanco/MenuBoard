package com.picanco.menuboard.domain;

import org.jetbrains.annotations.NotNull;

import java.time.Instant;

public class IndefiniteDateRange implements DateRange {
    @NotNull
    @Override
    public Instant getStart() {
        return Instant.ofEpochMilli(0);
    }

    @NotNull
    @Override
    public Instant getEnd() {
        return Instant.ofEpochMilli(Long.MAX_VALUE);
    }

    @Override
    public boolean contains(@NotNull Instant date) {
        return true;
    }

    @Override
    public boolean isOnOrAfter(@NotNull Instant date) {
        return true;
    }

    @Override
    public boolean isOnOrBefore(@NotNull Instant date) {
        return true;
    }

    @Override
    public boolean overlaps(@NotNull DateRange otherRange) {
        return true;
    }

    @Override
    public boolean contains(@NotNull DateRange dateRange) {
        return true;
    }
}
