package me.andreasmelone.amutillib.items.events;

@FunctionalInterface
public interface OnBlockBreakRunnable {
    void run(OnBlockBreakEvent event);
}
