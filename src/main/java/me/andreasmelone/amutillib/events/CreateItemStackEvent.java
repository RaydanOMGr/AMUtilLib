package me.andreasmelone.amutillib.events;

import me.andreasmelone.amutillib.items.AMItem;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class CreateItemStackEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final AMItem item;
    private final ItemStack itemStack;

    public CreateItemStackEvent(AMItem item, ItemStack itemStack) {
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
        return handlers;
    }
}
