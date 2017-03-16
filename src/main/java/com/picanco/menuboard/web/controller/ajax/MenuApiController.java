package com.picanco.menuboard.web.controller.ajax;

import com.picanco.menuboard.domain.MenuItem;
import com.picanco.menuboard.domain.MenuItemIdentifier;
import com.picanco.menuboard.service.MenuService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Collection;

@RestController
@RequestMapping("/api")
public class MenuApiController {

    @NotNull
    private final MenuService menuService;

    @Autowired
    public MenuApiController(@NotNull MenuService menuService) {
        this.menuService = menuService;
    }

    @RequestMapping(value = "/menuItems/all", method = RequestMethod.GET)
    public Collection<MenuItem> getAllMenuItems() {
        return menuService.retrieveAllMenuItems();
    }

    @RequestMapping(value = "/menuItems/", method = RequestMethod.GET)
    public Collection<MenuItem> getCurrentMenuItems() {
        return menuService.retrieveMenuItems(Instant.now());
    }

    @RequestMapping(value = "/menuItems/byDate/{date}", method = RequestMethod.GET)
    public Collection<MenuItem> getActiveMenuItems(@PathVariable("date") Instant instant) {
        return menuService.retrieveMenuItems(instant);
    }

    @RequestMapping(value = "/menuItems/save", method = RequestMethod.POST)
    public void saveMenuItem(@RequestBody MenuItem menuItem) {
        menuService.saveMenuItem(menuItem);
    }

    @RequestMapping(value = "/menuItems/remove/{id}", method = RequestMethod.GET)
    public void removeMenuItem(@PathVariable("id") MenuItemIdentifier identifier)  {
        menuService.removeMenuItem(identifier);
    }

    @RequestMapping(value = "/menuItems/{id}", method = RequestMethod.GET)
    public MenuItem retrieveMenuItem(@PathVariable("id") MenuItemIdentifier identifier) {
        return menuService.retrieveMenuItem(identifier);
    }

}
