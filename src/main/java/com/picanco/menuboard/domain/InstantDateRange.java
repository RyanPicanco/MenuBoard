package com.picanco.menuboard.domain;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;

public class InstantDateRange implements DateRange {

    @NotNull private final Instant start;
    @NotNull private final Instant end;

    public InstantDateRange(@Nullable Instant start, @Nullable Instant end) {
        this.start = start == null ? Instant.ofEpochMilli(0): start;
        this.end = end == null ? Instant.ofEpochMilli(Long.MAX_VALUE): end;
    }

    @Override
    @NotNull
    public Instant getStart() {
        return start;
    }

    @Override
    @NotNull
    public Instant getEnd() {
        return end;
    }

    @Override
    public boolean contains(@NotNull Instant date) {
        return isOnOrBefore(date) && isOnOrAfter(date);
    }

    @Override
    public boolean isOnOrAfter(@NotNull Instant date) {
        return this.end.equals(date) || this.end.isAfter(date);
    }

    @Override
    public boolean isOnOrBefore(@NotNull Instant date) {
        return this.start.equals(date) || this.start.isBefore(date);
    }

    @Override
    public boolean overlaps(@NotNull DateRange otherRange) {
        return this.start.isAfter(otherRange.getStart()) && this.start.isBefore(otherRange.getEnd())
            || this.end.isAfter(otherRange.getStart()) && this.end.isBefore(otherRange.getEnd());
    }

    @Override
    public boolean contains(@NotNull DateRange dateRange) {
        return this.isOnOrBefore(dateRange.getStart()) && this.isOnOrAfter(dateRange.getEnd());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InstantDateRange)) return false;

        InstantDateRange that = (InstantDateRange) o;

        if (!start.equals(that.start)) return false;
        return end.equals(that.end);

    }

    @Override
    public int hashCode() {
        int result = start.hashCode();
        result = 31 * result + end.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return start + "-" + end;
    }
}
