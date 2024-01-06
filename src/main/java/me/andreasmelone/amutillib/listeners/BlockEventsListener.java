package me.andreasmelone.amutillib.listeners;

import me.andreasmelone.amutillib.blocks.BlockRegister;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockEventsListener implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        BlockRegister.getInstance().getRegisteredElements().forEach((key, amBlock) -> {
            if(amBlock.compareTo(event.getBlock())) {
                amBlock.getOnBlockPlace().forEach(runnable -> runnable.run(event));
            }
        });
    }
}
