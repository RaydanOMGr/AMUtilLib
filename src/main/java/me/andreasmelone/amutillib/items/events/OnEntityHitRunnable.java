package me.andreasmelone.amutillib.items.events;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

@FunctionalInterface
public interface OnEntityHitRunnable {
    void run(EntityDamageByEntityEvent event);
}
