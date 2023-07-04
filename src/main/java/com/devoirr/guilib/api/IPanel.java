package com.devoirr.guilib.api;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface IPanel {

    String getTitle();
    int getSize();

    void setItems();
    void fillEmpty(ItemStack stack);

    void open(Player player);

}
