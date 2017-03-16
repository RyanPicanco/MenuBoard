package com.picanco.menuboard.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Pricing {

    @NotNull
    private final Collection<EffectivePrice> prices;

    public Pricing(@NotNull Collection<EffectivePrice> prices) {
        this.prices = prices;
    }

    public Pricing() {
        this.prices = new HashSet<>();
    }

    @NotNull
    @JsonValue
    public Collection<EffectivePrice> getPrices() {
        return prices;
    }

    @NotNull
    public Collection<EffectivePrice> getCurrentPrice(@NotNull Instant date) {
        return this.prices.parallelStream()
                          .filter(price -> price.getEffectiveDate().contains(date))
                          .collect(Collectors.groupingBy(EffectivePrice::getSize))
                          .entrySet()
                          .parallelStream()
                          .map(Map.Entry::getValue)
                          .map(this::getMostSpecificPrice)
                          .collect(Collectors.toSet());


    }

    private EffectivePrice getMostSpecificPrice(List<EffectivePrice> effectivePrices) {
        effectivePrices.sort(this::sortPrices);
        return effectivePrices.get(0);
    }

    private int sortPrices(EffectivePrice price1, EffectivePrice price2) {
        return price1.getEffectiveDate().contains(price2.getEffectiveDate()) ? 1 : -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pricing)) return false;

        Pricing pricing = (Pricing) o;

        return prices.equals(pricing.prices);

    }

    @Override
    public int hashCode() {
        return prices.hashCode();
    }

    public void restrictTo(Instant date) {
        final Collection<EffectivePrice> currentPrices = this.getCurrentPrice(date);

        this.prices.removeIf(p -> !currentPrices.contains(p));
    }
}
