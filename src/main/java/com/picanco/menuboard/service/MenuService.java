package com.picanco.menuboard.service;

import com.picanco.menuboard.domain.EffectivePrice;
import com.picanco.menuboard.domain.MenuItem;
import com.picanco.menuboard.domain.MenuItemIdentifier;
import com.picanco.menuboard.exception.MenuItemNotExistsException;
import com.picanco.menuboard.exception.PriceInConflictException;
import com.picanco.menuboard.repository.MenuRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
public class MenuService {

    @NotNull private final MenuRepository menuRepository;

    @Autowired
    public MenuService(@NotNull MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public void saveMenuItem(@NotNull MenuItem menuItem) throws PriceInConflictException {
        validatePricing(menuItem);

        menuRepository.save(menuItem);
    }

    @NotNull
    public MenuItem retrieveMenuItem(MenuItemIdentifier identifier) {
        @Nullable final MenuItem menuItem = menuRepository.retrieveMenuItem(identifier);

        if (menuItem == null) {
            throw new MenuItemNotExistsException(identifier);
        }

        return menuItem;
    }

    public void removeMenuItem(@NotNull MenuItemIdentifier identifier) {
        menuRepository.delete(identifier);
    }

    public Collection<MenuItem> retrieveAllMenuItems() {
        return menuRepository.retrieveAllMenuItems();
    }

    public Collection<MenuItem> retrieveMenuItems(Instant date) {
        final Collection<MenuItem> menuItems = menuRepository.retrieveActiveMenuItems(date);
        menuItems.forEach(m -> m.getPricing().restrictTo(date));
        return menuItems;
    }

    private void validatePricing(MenuItem menuItem) throws PriceInConflictException {
        for (EffectivePrice effectivePrice : menuItem.getPricing().getPrices()) {
            final Set<EffectivePrice> conflictingPrices = getConflictingPricing(menuItem, effectivePrice);

            if (!conflictingPrices.isEmpty()) {
                throw new PriceInConflictException(conflictingPrices);
            }
        }
    }

    private Set<EffectivePrice> getConflictingPricing(MenuItem menuItem, EffectivePrice effectivePrice) {
        return menuItem.getPricing()
                       .getPrices()
                       .stream()
                       .filter(p -> p != effectivePrice)
                       .filter(effectivePrice::conflictsWith)
                       .collect(toSet());
    }
}
