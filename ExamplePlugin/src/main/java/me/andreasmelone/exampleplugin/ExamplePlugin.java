package me.andreasmelone.exampleplugin;

import me.andreasmelone.amutillib.AMUtilLib;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExamplePlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        if(getServer().getPluginManager().getPlugin("AMUtilLib") == null) {
            getLogger().severe("AMUtilLib not found! Disabling plugin...");
            getLogger().severe("Download it here: https://github.com/RaydanOMGr/AMUtilLib/releases");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        Items.register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static ExamplePlugin getInstance() {
        return getPlugin(ExamplePlugin.class);
    }
}
