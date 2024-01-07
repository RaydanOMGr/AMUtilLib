package me.andreasmelone.amutillib.events.register;

import me.andreasmelone.amutillib.events.ServerTickEvent;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class ServerTickRunnable extends BukkitRunnable {
    @Override
    public void run() {
        Bukkit.getPluginManager().callEvent(new ServerTickEvent(Bukkit.getServer()));
    }
}
