package me.andreasmelone.amutillib.items.events;

import me.andreasmelone.amutillib.items.AMItem;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.ItemStack;

public class OnBlockBreakEvent implements Cancellable {
    private final Player player;
    private final Block block;
    private final ItemStack item;
    private final AMItem amItem;

    private boolean cancelled = false;

    public OnBlockBreakEvent(Player player, Block block, ItemStack item, AMItem amItem) {
        this.player = player;
        this.block = block;
        this.item = item;
        this.amItem = amItem;
    }

    public Player getPlayer() {
        return player;
    }

    public Block getBlock() {
        return block;
    }

    public ItemStack getItem() {
        return item;
    }

    public AMItem getAMItem() {
        return amItem;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
