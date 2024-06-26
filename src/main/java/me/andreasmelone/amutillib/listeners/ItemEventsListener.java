package me.andreasmelone.amutillib.listeners;

import me.andreasmelone.amutillib.AMUtilLib;
import me.andreasmelone.amutillib.events.CreateItemStackEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemEventsListener implements Listener {
    private final AMUtilLib lib;
    public ItemEventsListener(AMUtilLib lib) {
        this.lib = lib;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if(item != null) {
            lib.getItemRegister().getRegisteredElements().forEach((key, amItem) -> {
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
        ItemStack item = player.getInventory().getItemInMainHand();
        lib.getItemRegister().getRegisteredElements().forEach((key, amItem) -> {
            if (item.getItemMeta() != null) {
                if (amItem.compareTo(item)) {
                    amItem.getOnBlockBreak().forEach(runnable -> runnable.run(event));
                }
            }
        });
    }

    @EventHandler
    public void onItemCreate(CreateItemStackEvent event) {
        lib.getItemRegister().getRegisteredElements().forEach((key, amItem) -> {
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
        lib.getItemRegister().getRegisteredElements().forEach((key, amItem) -> {
            if (item.getItemMeta() != null) {
                if (amItem.compareTo(item)) {
                    amItem.getOnEntityHit().forEach(runnable -> runnable.run(event));
                }
            }
        });
    }
}
