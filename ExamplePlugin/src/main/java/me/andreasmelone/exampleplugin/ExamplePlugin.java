package me.andreasmelone.exampleplugin;

import me.andreasmelone.amutillib.AMUtilLib;
import me.andreasmelone.amutillib.i18n.I18n;
import me.andreasmelone.amutillib.utils.CommandUtil;
import me.andreasmelone.exampleplugin.commands.ReloadCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExamplePlugin extends JavaPlugin {
    private final I18n i18n = new I18n(this);
    public FileConfiguration config;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();

        i18n.setLanguage(config.getString("lang"));

        Items.register();
        Blocks.register();

        AMUtilLib.getInstance().registerCommands(this, "giveitem");
        AMUtilLib.getInstance().registerEvents(this);

        CommandUtil.registerTabExecutor(this, new ReloadCommand(this), "reloadexample");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static ExamplePlugin getInstance() {
        return getPlugin(ExamplePlugin.class);
    }

    public I18n getI18n() {
        return i18n;
    }
}
