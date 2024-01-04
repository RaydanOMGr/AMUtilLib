package me.andreasmelone.exampleplugin;

import me.andreasmelone.amutillib.AMUtilLib;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExamplePlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        Items.register();

        // Plugin startup logic
        AMUtilLib.getInstance().registerEvents(this);
        AMUtilLib.getInstance().registerCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static ExamplePlugin getInstance() {
        return getPlugin(ExamplePlugin.class);
    }
}
