package me.andreasmelone.amutillib.listeners;

import me.andreasmelone.amutillib.items.execute.OnExecuteEvent;
import me.andreasmelone.amutillib.registry.ItemRegister;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class ItemEventsListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Entity interactedEntity = null;
        EquipmentSlot hand = event.getHand();
        ItemStack item = event.getItem();
        if(item != null) {
            Block interactedBlock = event.getClickedBlock();

            ItemRegister.getInstance().getRegisteredItems().forEach((key, amItem) -> {
                if(item.getItemMeta() != null) {
                    if(amItem.compareTo(item)) {
                        OnExecuteEvent onExecuteEvent
                                = new OnExecuteEvent(player, interactedEntity, interactedBlock, hand, item, amItem);
                        amItem.getOnInteract().run(onExecuteEvent);
                    }
                }
            });
        }
    }
}
