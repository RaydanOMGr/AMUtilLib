package me.andreasmelone.amutillib.listeners;

import com.jeff_media.customblockdata.CustomBlockData;
import me.andreasmelone.amutillib.AMUtilLib;
import me.andreasmelone.amutillib.blocks.BlockRegister;
import me.andreasmelone.amutillib.items.ItemRegister;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class BlockEventsListener implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        ItemRegister.getInstance().getRegisteredElements().forEach((key, amItem) -> {
            if(amItem.compareTo(event.getItemInHand())) {
                PersistentDataContainer pdc = new CustomBlockData(event.getBlock(), amItem.getKey().getNamespace());
                NamespacedKey k = new NamespacedKey(amItem.getKey().getNamespace(), "block_id");
                pdc.set(k, PersistentDataType.STRING, amItem.getKey().getKey());
            }
        });

        BlockRegister.getInstance().getRegisteredElements().forEach((key, amBlock) -> {
            if(amBlock.compareTo(event.getBlock())) {
                amBlock.getOnBlockPlace().forEach(runnable -> runnable.run(event));
            }
        });
    }

    @EventHandler
    public void onBlockInteract(PlayerInteractEvent event) {
        if(event.getClickedBlock() == null) return;
        BlockRegister.getInstance().getRegisteredElements().forEach((key, amBlock) -> {
            if(amBlock.compareTo(event.getClickedBlock())) {
                amBlock.getOnInteract().forEach(runnable -> runnable.run(event));
            }
        });
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        BlockRegister.getInstance().getRegisteredElements().forEach((key, amBlock) -> {
            if(amBlock.compareTo(event.getBlock())) {
                amBlock.getOnBlockBreak().forEach(runnable -> runnable.run(event));
                // make it drop the item
                event.setDropItems(false);
                event.getBlock().getWorld().dropItemNaturally(
                        event.getBlock().getLocation(),
                        amBlock.getItem().createItemStack()
                );
            }
        });
    }
}
