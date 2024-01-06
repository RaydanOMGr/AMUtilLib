package me.andreasmelone.amutillib.items.events;

import me.andreasmelone.amutillib.events.CreateItemStackEvent;

@FunctionalInterface
public interface OnCreateItemStackRunnable {
    void run(CreateItemStackEvent event);
}
