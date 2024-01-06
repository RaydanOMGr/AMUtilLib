package me.andreasmelone.amutillib.blocks.events;

import org.bukkit.event.block.BlockPlaceEvent;

@FunctionalInterface
public interface OnBlockPlaceRunnable {
    void run(BlockPlaceEvent event);
}
