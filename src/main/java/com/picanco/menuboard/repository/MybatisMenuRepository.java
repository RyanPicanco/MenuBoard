package com.picanco.menuboard.repository;

import com.picanco.menuboard.domain.EffectivePrice;
import com.picanco.menuboard.domain.MenuItem;
import com.picanco.menuboard.domain.MenuItemIdentifier;
import com.picanco.menuboard.repository.mybatis.MenuMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Collection;

@Repository
public class MybatisMenuRepository implements MenuRepository {

    @NotNull private final MenuMapper menuMapper;

    @Autowired
    public MybatisMenuRepository(@NotNull MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @Override
    public void save(@NotNull MenuItem menuItem) {
        menuMapper.saveMenuItem(menuItem);

        menuItem.getPricing().getPrices().forEach(price -> savePrice(menuItem, price));
    }

    @Override
    public void delete(@NotNull MenuItemIdentifier identifier) {
        menuMapper.deleteMenuItem(identifier);
    }

    @Nullable
    @Override
    public MenuItem retrieveMenuItem(@NotNull MenuItemIdentifier identifier) {
        return menuMapper.retrieveMenuItem(identifier);
    }

    @NotNull
    @Override
    public Collection<MenuItem> retrieveAllMenuItems() {
        return menuMapper.retrieveAllMenuItems();
    }

    @NotNull
    @Override
    public Collection<MenuItem> retrieveActiveMenuItems(Instant date) {
        return menuMapper.retrieveActiveMenuItems(date);
    }

    private void savePrice(@NotNull MenuItem menuItem, EffectivePrice price) {
        menuMapper.savePrice(menuItem.getIdentifier(), price);
    }
}

