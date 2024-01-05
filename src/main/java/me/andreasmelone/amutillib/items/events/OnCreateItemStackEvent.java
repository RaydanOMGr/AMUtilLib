package me.andreasmelone.amutillib.items.events;

import me.andreasmelone.amutillib.items.AMItem;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class OnCreateItemStackEvent extends Event {
    HandlerList handlers = new HandlerList();

    private final AMItem item;
    private final ItemStack itemStack;

    public OnCreateItemStackEvent(AMItem item, ItemStack itemStack) {
        this.item = item;
        this.itemStack = itemStack;
    }

    public AMItem getItem() {
        return item;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return new HandlerList();
    }
}
