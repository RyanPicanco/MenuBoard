package com.picanco.menuboard.repository.mybatis;

import com.picanco.menuboard.domain.EffectivePrice;
import com.picanco.menuboard.domain.MenuItem;
import com.picanco.menuboard.domain.MenuItemIdentifier;
import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.Collection;

@Mapper
public interface MenuMapper {

    String SELECT_MENU_ITEMS =
            " SELECT m.ID, m.StartDate as MenuItemStartDate, m.EndDate as MenuItemEndDate, m.Description, m.ImageData, " +
            " p.PriceID, p.Cost, p.Size, p.StartDate as PriceStartDate, p.EndDate as PriceEndDate,                     " +
            " CASE WHEN m.StartDate is null THEN 0 ELSE 1 END AS MenuItemHasDates,                                     " +
            " CASE WHEN p.StartDate is null THEN 0 ELSE 1 END AS PriceHasDates                                         " +
            " FROM Menu.MenuItems m                                                                                    " +
            " JOIN Menu.MenuItemPrices p on m.ID = p.MenuItemID                                                        ";

    @Insert({
            "  INSERT INTO Menu.MenuItems (ID, Description, StartDate, EndDate, ImageData)     ",
            "  VALUES (#{identifier}, #{description} #{startDate}, #{endDate}, #{image})  ",
            "  ON DUPLICATE KEY UPDATE                                                    ",
            "   StartDate = #{startDate}, EndDate = #{endDate}, ImageData = #{image}      "
    })
    void saveMenuItem(@NotNull MenuItem menuItem);


    @Insert({
            "   INSERT INTO Menu.MenuItemPrices (MenuItemID, PriceID, Size, StartDate, EndDate, Cost)   ",
            "   VALUES (#{identifier}, #{price.priceIdentifier}, #{price.size},                    ",
            "          #{price.effectiveDate.start} #{price.effectiveDate.end}, #{price.cost}      ",
            "   ON DUPLICATE KEY UPDATE                                                            ",
            "     Size = #{price.size},                                                            ",
            "     StartDate = #{price.effectiveDate.start},                                        ",
            "     EndDate = #{price.effectiveDate.end},                                            ",
            "     Cost = #{price.cost}                                                             "
    })
    void savePrice(@Param("identifier") @NotNull MenuItemIdentifier identifier, @NotNull @Param("price") EffectivePrice price);


    @Delete({
            "  DELETE * FROM Menu.MenuItem WHERE ID = #{identifier}  "
    })
    void deleteMenuItem(@NotNull MenuItemIdentifier identifier);

    @Select({
            SELECT_MENU_ITEMS,
            "   WHERE m.ID = #{identifier} "
    })
    @ResultMap("menuItemResultMap")
    @Nullable MenuItem retrieveMenuItem(@NotNull MenuItemIdentifier identifier);

    @Select({
            SELECT_MENU_ITEMS
    })
    @ResultMap("menuItemResultMap")
    @NotNull Collection<MenuItem> retrieveAllMenuItems();

    @Select({
            SELECT_MENU_ITEMS,
            " WHERE (m.StartDate IS NULL OR m.StartDate <= #{date}) AND (m.EndDate IS NULL OR m.EndDate >= #{date}) ",
            "   AND (p.StartDate IS NULL OR p.StartDate <= #{date}) AND (p.EndDate IS NULL OR p.EndDate >= #{date}) "
    })
    @ResultMap("menuItemResultMap")
    @NotNull Collection<MenuItem> retrieveActiveMenuItems(Instant date);
}
