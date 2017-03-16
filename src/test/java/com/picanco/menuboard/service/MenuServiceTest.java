package com.picanco.menuboard.service;

import com.picanco.menuboard.domain.*;
import com.picanco.menuboard.exception.MenuItemNotExistsException;
import com.picanco.menuboard.exception.PriceInConflictException;
import com.picanco.menuboard.repository.MenuRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MenuServiceTest {

    @Mock
    private MenuRepository menuRepository;

    private MenuService serviceToTest;

    @Before
    public void setUp() throws Exception {
        serviceToTest = new MenuService(menuRepository);

    }

    @Test(expected = PriceInConflictException.class)
    public void addingConflictingMenuItemThrowsException() throws Exception {
        final MenuItem itemToAdd = createMenuItem(1, LocalDate.of(2017, 1, 1), LocalDate.of(2017, 1, 31), "MenuItem 1",
                                                  createPrice(1, 5.99f, "Combo", LocalDate.of(2017, 1, 10), LocalDate.of(2017, 1, 16)),
                                                  createPrice(2, 4.99f, "Combo", LocalDate.of(2017, 1, 15), LocalDate.of(2017, 1, 21)));

        try {
            serviceToTest.saveMenuItem(itemToAdd);
        } finally {
            verify(menuRepository, never()).save(itemToAdd);
        }
    }

    @Test
    public void pricesForDifferentSizesDoNotConflict() throws Exception {
        final MenuItem itemToAdd = createMenuItem(1, LocalDate.of(2017, 1, 1), LocalDate.of(2017, 1, 31), "MenuItem 1",
                                                  createPrice(1, 5.99f, "Single", LocalDate.of(2017, 1, 10), LocalDate.of(2017, 1, 16)),
                                                  createPrice(2, 4.99f, "Combo", LocalDate.of(2017, 1, 15), LocalDate.of(2017, 1, 21)));

        serviceToTest.saveMenuItem(itemToAdd);

        verify(menuRepository).save(itemToAdd);

    }

    @Test
    public void addingNonConflictingMenuItemSucceeds() throws Exception {
        final MenuItem itemToAdd = createMenuItem(1, LocalDate.of(2017, 1, 1), LocalDate.of(2017, 1, 31), "MenuItem 1",
                                                  createPrice(1, 5.99f, "Combo", LocalDate.of(2017, 1, 10), LocalDate.of(2017, 1, 16)),
                                                  createPrice(2, 4.99f, "Combo", LocalDate.of(2017, 1, 17), LocalDate.of(2017, 1, 23)));

        serviceToTest.saveMenuItem(itemToAdd);

        verify(menuRepository).save(itemToAdd);

    }

    @Test
    public void removingAddedItemsSucceeds() throws Exception {
        final MenuItemIdentifier identifier = MenuItemIdentifier.create(1);

        serviceToTest.removeMenuItem(identifier);

        verify(menuRepository).delete(identifier);
    }

    @Test(expected = MenuItemNotExistsException.class)
    public void retrievingNonExistentMenuItemsThrowsException() throws Exception {
        MenuItemIdentifier identifier = MenuItemIdentifier.create(1);
        when(menuRepository.retrieveMenuItem(identifier)).thenReturn(null);

        try {
            serviceToTest.retrieveMenuItem(identifier);
        } finally {
            verify(menuRepository).retrieveMenuItem(identifier);
        }

    }

    @Test
    public void retrievingMenuItemsSucceeds() throws Exception {
        final MenuItem result = createMenuItem(1, LocalDate.of(2017, 1, 1), LocalDate.of(2017, 1, 31), "MenuItem 1",
                                               createPrice(1, 5.99f, "Combo", LocalDate.of(2017, 1, 10), LocalDate.of(2017, 1, 16)),
                                               createPrice(2, 4.99f, "Combo", LocalDate.of(2017, 1, 17), LocalDate.of(2017, 1, 23)));

        when(menuRepository.retrieveMenuItem(result.getIdentifier())).thenReturn(result);

        final MenuItem menuItem = serviceToTest.retrieveMenuItem(result.getIdentifier());

        verify(menuRepository).retrieveMenuItem(result.getIdentifier());
        assertEquals(menuItem, result);
    }

    private MenuItem createMenuItem(int identifier,
                                    LocalDate start,
                                    LocalDate end,
                                    String description,
                                    EffectivePrice... prices) {
        return new MenuItem(MenuItemIdentifier.create(identifier),
                            new InstantDateRange(toInstant(start), toInstant(end)),
                            new Description(description),
                            new Pricing(Arrays.asList(prices)),
                            new ByteArrayProductImage(new byte[] {}));
    }

    private Instant toInstant(LocalDate date) {
        return date.atStartOfDay().toInstant(ZoneOffset.UTC);
    }

    private EffectivePrice createPrice(Integer priceIdentifier, Float cost, String size, LocalDate start, LocalDate end) {
        return new EffectivePrice(new PriceIdentifier(priceIdentifier), new Size(size), new InstantDateRange(toInstant(start), toInstant(end)), new Cost(cost));
    }
}