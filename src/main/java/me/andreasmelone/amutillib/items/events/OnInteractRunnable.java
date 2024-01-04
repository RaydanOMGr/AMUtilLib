package me.andreasmelone.amutillib.items.events;

import org.bukkit.event.player.PlayerInteractEvent;

@FunctionalInterface
public interface OnInteractRunnable {
    void run(PlayerInteractEvent event);
}
