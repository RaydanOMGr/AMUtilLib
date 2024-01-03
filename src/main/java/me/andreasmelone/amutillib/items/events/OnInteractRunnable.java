package me.andreasmelone.amutillib.items.events;

@FunctionalInterface
public interface OnInteractRunnable {
    void run(OnInteractEvent event);
}
