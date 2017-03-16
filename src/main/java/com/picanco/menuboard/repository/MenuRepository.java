package com.picanco.menuboard.repository;

import com.picanco.menuboard.domain.MenuItem;
import com.picanco.menuboard.domain.MenuItemIdentifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.Collection;

public interface MenuRepository {

    void save(@NotNull MenuItem menuItem);

    void delete(@NotNull MenuItemIdentifier identifier);

    @Nullable MenuItem retrieveMenuItem(@NotNull MenuItemIdentifier identifier);

    @NotNull Collection<MenuItem> retrieveAllMenuItems();

    @NotNull Collection<MenuItem> retrieveActiveMenuItems(Instant date);
}
