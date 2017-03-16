package com.picanco.menuboard.domain;

import org.jetbrains.annotations.NotNull;

import java.time.Instant;

public interface DateRange {
    @NotNull
    Instant getStart();

    @NotNull
    Instant getEnd();

    boolean contains(@NotNull Instant date);

    boolean isOnOrAfter(@NotNull Instant date);

    boolean isOnOrBefore(@NotNull Instant date);

    boolean overlaps(@NotNull DateRange otherRange);

    boolean contains(@NotNull DateRange dateRange);
}
