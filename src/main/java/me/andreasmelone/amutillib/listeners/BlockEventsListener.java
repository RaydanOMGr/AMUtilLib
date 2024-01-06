package me.andreasmelone.amutillib.listeners;

import com.jeff_media.customblockdata.CustomBlockData;
import me.andreasmelone.amutillib.blocks.BlockRegister;
import me.andreasmelone.amutillib.items.ItemRegister;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

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
}
