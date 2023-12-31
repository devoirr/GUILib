package com.devoirr.guilib.containers;

import com.devoirr.guilib.PanelListener;
import com.devoirr.guilib.api.IPanel;
import com.devoirr.guilib.api.annotations.Click;
import com.devoirr.guilib.api.annotations.Close;
import com.devoirr.guilib.api.annotations.Drag;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public abstract class Panel implements InventoryHolder, IPanel {

    protected Player player;
    protected Inventory inventory;

    private final Set<Method> close;
    private final Set<Method> drag;
    private final Set<ClickMethod> click;

    public Panel(JavaPlugin plugin) {

        PanelListener.register(plugin);

        this.close = new HashSet<>();
        this.click = new HashSet<>();
        this.drag = new HashSet<>();

        for (Method method : getClass().getMethods()) {
            if (method.isAnnotationPresent(Click.class))
                click.add(new ClickMethod(method, method.getDeclaredAnnotation(Click.class).slots()));
            else if (method.isAnnotationPresent(Close.class))
                close.add(method);
            else if (method.isAnnotationPresent(Drag.class)) {
                drag.add(method);
            }
        }

    }

    @Override
    public void open(Player player) {

        this.player = player;

        this.inventory = Bukkit.createInventory(this, getSize(), getTitle());
        this.setItems();

        player.openInventory(inventory);

    }

    @Override
    public void fillEmpty(ItemStack stack) {
        if (inventory == null) return;

        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) == null)
                inventory.setItem(i, stack);
        }
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public final void handleClick(InventoryClickEvent event) {
        click.forEach(m -> {
            try {
                if (m.getSlots().isEmpty() || m.getSlots().contains(event.getSlot()))
                    m.getMethod().invoke(this, event);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    public final void handleClose(InventoryCloseEvent event) {
        close.forEach(m -> {
            try {
                m.invoke(this, event);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    public final void handleDrag(InventoryDragEvent event) {
        drag.forEach(m -> {
            try {
                m.invoke(this, event);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

}
