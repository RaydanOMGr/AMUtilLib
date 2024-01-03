package me.andreasmelone.amutillib.items.events;

import me.andreasmelone.amutillib.items.AMItem;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class OnInteractEvent implements Cancellable {
    private final Player player;
    private final Entity interactedEntity;
    private final Block interactedBlock;
    private final EquipmentSlot hand;
    private final ItemStack item;
    private final AMItem amItem;

    private boolean cancelled = false;

    public OnInteractEvent(Player player, Entity interactedEntity, Block interactedBlock,
                           EquipmentSlot hand, ItemStack item, AMItem amItem) {
        this.player = player;
        this.interactedBlock = interactedBlock;
        this.interactedEntity = interactedEntity;
        this.hand = hand;
        this.item = item;
        this.amItem = amItem;
    }

    @Nonnull
    public Player getPlayer() {
        return player;
    }

    @Nullable
    public Entity getInteractedEntity() {
        return interactedEntity;
    }

    @Nullable
    public Block getInteractedBlock() {
        return interactedBlock;
    }

    @Nonnull
    public EquipmentSlot getHand() {
        return hand;
    }

    @Nonnull
    public ItemStack getItem() {
        return item;
    }

    @Nonnull
    public AMItem getAMItem() {
        return amItem;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }
}
