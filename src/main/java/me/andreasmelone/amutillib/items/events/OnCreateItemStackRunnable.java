package me.andreasmelone.amutillib.items.events;

@FunctionalInterface
public interface OnCreateItemStackRunnable {
    void run(OnCreateItemStackEvent event);
}
