package me.andreasmelone.amutillib.items.events;

import org.bukkit.event.block.BlockBreakEvent;

@FunctionalInterface
public interface OnBlockBreakRunnable {
    void run(BlockBreakEvent event);
}
