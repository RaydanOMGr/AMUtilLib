package me.andreasmelone.amutillib;

import me.andreasmelone.amutillib.commands.GiveItemCommand;
import me.andreasmelone.amutillib.listeners.ItemEventsListener;
import org.bukkit.plugin.java.JavaPlugin;

public class AMUtilLib {
    private static final AMUtilLib INSTANCE = new AMUtilLib();

    public void registerEvents(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new ItemEventsListener(), plugin);
    }

    public void registerGiveCommand(JavaPlugin plugin, String commandName) {
        if(plugin.getCommand(commandName) == null) {
            plugin.getLogger().warning("[AMUtilLib] Could not register command " + commandName + " for plugin " + plugin.getName() + " because it does not exist");
            plugin.getLogger().warning("[AMUtilLib] Please make sure to register the command in your plugin.yml");
            return;
        }
        plugin.getCommand(commandName).setExecutor(new GiveItemCommand());
        plugin.getCommand(commandName).setTabCompleter(new GiveItemCommand());
    }

    public static AMUtilLib getInstance() {
        return INSTANCE;
    }
}
