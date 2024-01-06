package me.andreasmelone.amutillib.events.register;

import me.andreasmelone.amutillib.events.ServerTickEvent;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class ServerTickRunnable extends BukkitRunnable {
    private static final ServerTickRunnable INSTANCE = new ServerTickRunnable();

    @Override
    public void run() {
        Bukkit.getPluginManager().callEvent(new ServerTickEvent(Bukkit.getServer()));
    }

    public static BukkitRunnable get() {
        return INSTANCE;
    }
}
