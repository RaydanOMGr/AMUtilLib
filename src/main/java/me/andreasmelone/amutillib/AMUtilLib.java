package me.andreasmelone.amutillib;

import me.andreasmelone.amutillib.commands.GiveItemCommand;
import me.andreasmelone.amutillib.utils.CommandUtil;
import me.andreasmelone.amutillib.listeners.ItemEventsListener;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

public final class AMUtilLib extends JavaPlugin {
    @Override
    public void onEnable() {
        registerEvents(this);
        registerCommands();
    }

    private void registerEvents(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new ItemEventsListener(), plugin);
        //plugin.getServer().getPluginManager().registerEvents(new CommandEventsListener(plugin), plugin);
    }

    private void registerCommands() {
        CommandMap commandMap = CommandUtil.getCommandMap();
        if(commandMap == null) return;
        if(commandMap.getCommand("giveitem") != null) return;

        CommandUtil.registerCommands(CommandUtil.toCommand(new GiveItemCommand(), "giveitem"));
    }

    public static AMUtilLib getInstance() {
        return getPlugin(AMUtilLib.class);
    }
}
