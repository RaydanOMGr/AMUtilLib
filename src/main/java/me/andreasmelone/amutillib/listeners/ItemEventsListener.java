package me.andreasmelone.amutillib.listeners;

import me.andreasmelone.amutillib.items.events.CreateItemStackEvent;
import me.andreasmelone.amutillib.items.ItemRegister;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class ItemEventsListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Entity interactedEntity = null;
        EquipmentSlot hand = event.getHand();
        Action action = event.getAction();
        ItemStack item = event.getItem();
        if(item != null) {
            Block interactedBlock = event.getClickedBlock();

            ItemRegister.getInstance().getRegisteredItems().forEach((key, amItem) -> {
                if(item.getItemMeta() != null) {
                    if(amItem.compareTo(item)) {
                        amItem.getOnInteract().forEach(runnable -> runnable.run(event));
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
                    amItem.getOnBlockBreak().forEach(runnable -> runnable.run(event));
                }
            }
        });
    }

    @EventHandler
    public void onItemCreate(CreateItemStackEvent event) {
        ItemRegister.getInstance().getRegisteredItems().forEach((key, amItem) -> {
            if (amItem.compareTo(event.getItemStack())) {
                amItem.getOnCreateItemStack().forEach(runnable -> runnable.run(event));
            }
        });
    }

    @EventHandler
    public void onEntityHit(EntityDamageByEntityEvent event) {
        if(!(event.getDamager() instanceof Player)) return;
        Player player = (Player) event.getDamager();
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemRegister.getInstance().getRegisteredItems().forEach((key, amItem) -> {
            if (item.getItemMeta() != null) {
                if (amItem.compareTo(item)) {
                    amItem.getOnEntityHit().forEach(runnable -> runnable.run(event));
                }
            }
        });
    }
}
