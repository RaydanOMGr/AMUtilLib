package me.andreasmelone.amutillib;

import me.andreasmelone.amutillib.commands.GiveItemCommand;
import me.andreasmelone.amutillib.utils.CommandUtil;
import me.andreasmelone.amutillib.listeners.ItemEventsListener;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

public class AMUtilLib {
    private static final AMUtilLib INSTANCE = new AMUtilLib();

    public void registerEvents(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new ItemEventsListener(), plugin);
        //plugin.getServer().getPluginManager().registerEvents(new CommandEventsListener(plugin), plugin);
    }

    public void registerCommands() {
        CommandMap commandMap = CommandUtil.getCommandMap();
        if(commandMap == null) return;
        if(commandMap.getCommand("giveitem") != null) return;

        CommandUtil.registerCommands(CommandUtil.toCommand(new GiveItemCommand(), "giveitem"));
    }

    public static AMUtilLib getInstance() {
        return INSTANCE;
    }
}
