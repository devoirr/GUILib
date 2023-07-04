package com.devoirr.guilib;

import com.devoirr.guilib.api.annotations.Click;
import com.devoirr.guilib.api.annotations.Close;
import com.devoirr.guilib.containers.Panel;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class ExamplePanel extends Panel {

    public ExamplePanel(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public String getTitle() {
        return "Example menu";
    }

    @Override
    public int getSize() {
        return 9;
    }

    @Override
    public void setItems() {

        inventory.setItem(3, new ItemStack(Material.COMPASS));
        inventory.setItem(5, new ItemStack(Material.BONE));

        fillEmpty(new ItemStack(Material.BLACK_STAINED_GLASS_PANE));

    }

    @Click(slots = 3)
    public void onCompass(InventoryClickEvent event) {
        player.sendMessage("Вы нажали на компасс.");
    }

    @Click(slots = 5)
    public void onBone(InventoryClickEvent event) {
        player.sendMessage("Вы нажали на кость.");
        player.playSound(player.getLocation(), Sound.ENTITY_SKELETON_HURT, 1f, 1f);
    }

    @Close
    public void onClose(InventoryCloseEvent event) {
        player.sendMessage("Вы закрыли меню.");
    }

}
