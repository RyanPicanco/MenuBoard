package com.picanco.menuboard.domain;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EffectivePrice {

    @NotNull private final PriceIdentifier priceIdentifier;
    @NotNull private final Size size;
    @NotNull private final DateRange effectiveDate;
    @NotNull private final Cost cost;

    public EffectivePrice(@NotNull PriceIdentifier priceIdentifier,
                          @NotNull Size size,
                          @Nullable DateRange effectiveDate,
                          @NotNull Cost cost) {
        this.priceIdentifier = priceIdentifier;
        this.size = size;
        this.effectiveDate = effectiveDate == null ? new IndefiniteDateRange() : effectiveDate;
        this.cost = cost;
    }

    @NotNull
    public PriceIdentifier getPriceIdentifier() {
        return priceIdentifier;
    }

    @NotNull
    public Cost getCost() {
        return cost;
    }

    @NotNull
    public Size getSize() {
        return size;
    }

    @NotNull
    public DateRange getEffectiveDate() {
        return effectiveDate;
    }

    public boolean conflictsWith(EffectivePrice otherPrice) {
        if (!this.getSize().equals(otherPrice.getSize())) {
            return false;
        }

        final DateRange myDate = this.getEffectiveDate();
        final DateRange otherDate = otherPrice.getEffectiveDate();

        return myDate.equals(otherDate) || (myDate.overlaps(otherDate) && !thereIsContainment(myDate, otherDate));

    }

    private boolean thereIsContainment(DateRange myDate, DateRange otherDate) {
        return myDate.contains(otherDate) || otherDate.contains(myDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EffectivePrice)) return false;

        EffectivePrice that = (EffectivePrice) o;

        if (!cost.equals(that.cost)) return false;
        if (!size.equals(that.size)) return false;
        return effectiveDate.equals(that.effectiveDate);

    }

    @Override
    public int hashCode() {
        int result = cost.hashCode();
        result = 31 * result + size.hashCode();
        result = 31 * result + effectiveDate.hashCode();
        return result;
    }
}
