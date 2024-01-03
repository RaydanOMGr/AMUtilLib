package me.andreasmelone.amutillib.items.execute;

@FunctionalInterface
public interface OnInteractRunnable {
    void run(OnExecuteEvent event);
}
