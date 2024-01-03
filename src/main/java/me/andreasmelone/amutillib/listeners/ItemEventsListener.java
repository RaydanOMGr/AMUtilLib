package me.andreasmelone.amutillib.listeners;

import me.andreasmelone.amutillib.items.events.OnBlockBreakEvent;
import me.andreasmelone.amutillib.items.events.OnInteractEvent;
import me.andreasmelone.amutillib.registry.ItemRegister;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class ItemEventsListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if(event.getAction().toString().startsWith("LEFT")) return; // Only execute on right click (not left click)
        Player player = event.getPlayer();
        Entity interactedEntity = null;
        EquipmentSlot hand = event.getHand();
        ItemStack item = event.getItem();
        if(item != null) {
            Block interactedBlock = event.getClickedBlock();

            ItemRegister.getInstance().getRegisteredItems().forEach((key, amItem) -> {
                if(item.getItemMeta() != null) {
                    if(amItem.compareTo(item)) {
                        OnInteractEvent onInteractEvent
                                = new OnInteractEvent(player, interactedEntity, interactedBlock, hand, item, amItem);
                        amItem.getOnInteract().run(onInteractEvent);
                        event.setCancelled(onInteractEvent.isCancelled());
                    }
                }
            });
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemRegister.getInstance().getRegisteredItems().forEach((key, amItem) -> {
            if (item.getItemMeta() != null) {
                if (amItem.compareTo(item)) {
                    OnBlockBreakEvent onBlockBreakEvent
                            = new OnBlockBreakEvent(player, block, item, amItem);
                    amItem.getOnBlockBreak().run(onBlockBreakEvent);
                    event.setCancelled(onBlockBreakEvent.isCancelled());
                }
            }
        });
    }
}
