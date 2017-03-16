package com.picanco.menuboard.domain;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MenuItem {

    @NotNull private final MenuItemIdentifier identifier;
    @NotNull private final DateRange effectiveDate;
    @NotNull private final Description menuDescription;
    @NotNull private Pricing pricing;
    @NotNull private final ProductImage image;

    public MenuItem(@NotNull MenuItemIdentifier identifier,
                    @Nullable DateRange effectiveDate,
                    @NotNull Description menuDescription,
                    @NotNull ProductImage image) {
        this(identifier, effectiveDate, menuDescription, new Pricing(), image);
    }

    public MenuItem(@NotNull MenuItemIdentifier identifier,
                    @Nullable DateRange effectiveDate,
                    @NotNull Description menuDescription,
                    @NotNull Pricing pricing,
                    @NotNull ProductImage image) {
        this.identifier = identifier;
        this.effectiveDate = effectiveDate == null ? new IndefiniteDateRange() : effectiveDate;
        this.menuDescription = menuDescription;
        this.pricing = pricing;
        this.image = image;
    }

    @NotNull
    public MenuItemIdentifier getIdentifier() {
        return identifier;
    }

    @NotNull
    public DateRange getEffectiveDate() {
        return effectiveDate;
    }

    @NotNull
    public Description getMenuDescription() {
        return menuDescription;
    }

    @NotNull
    public Pricing getPricing() {
        return pricing;
    }

    public void setPricing(@NotNull Pricing pricing) {
        this.pricing = pricing;
    }

    @NotNull
    public ProductImage getImage() {
        return image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;

        MenuItem menuItem = (MenuItem) o;

        if (!identifier.equals(menuItem.identifier)) return false;
        if (!effectiveDate.equals(menuItem.effectiveDate)) return false;
        if (!menuDescription.equals(menuItem.menuDescription)) return false;
        if (!pricing.equals(menuItem.pricing)) return false;
        return image.equals(menuItem.image);

    }

    @Override
    public int hashCode() {
        int result = identifier.hashCode();
        result = 31 * result + effectiveDate.hashCode();
        result = 31 * result + menuDescription.hashCode();
        result = 31 * result + pricing.hashCode();
        result = 31 * result + image.hashCode();
        return result;
    }
}
