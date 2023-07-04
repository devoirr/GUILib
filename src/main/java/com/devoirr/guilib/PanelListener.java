package com.devoirr.guilib;

import com.devoirr.guilib.containers.Panel;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class PanelListener implements Listener {

    private static final Map<JavaPlugin, Listener> listenerMap = new HashMap<>();
    public static void register(JavaPlugin plugin) {
        if (!listenerMap.containsKey(plugin)) {
            PanelListener panelListener = new PanelListener();

            listenerMap.put(plugin, panelListener);
            Bukkit.getPluginManager().registerEvents(panelListener, plugin);
        }
    }

    @EventHandler
    public void listenToClick(InventoryClickEvent event) {

        if (event.getClickedInventory() == null) return;

        Inventory inventory = event.getClickedInventory();
        if (!(inventory.getHolder() instanceof Panel)) return;

        Panel panel = (Panel) inventory.getHolder();

        event.setCancelled(true);
        panel.handleClick(event);

    }

    @EventHandler
    public void listenToClose(InventoryCloseEvent event) {

        Inventory inventory = event.getInventory();
        if (!(inventory.getHolder() instanceof Panel)) return;

        Panel panel = (Panel) inventory.getHolder();

        panel.handleClose(event);

    }

}
