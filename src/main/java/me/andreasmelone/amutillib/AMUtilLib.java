package me.andreasmelone.amutillib;

import me.andreasmelone.amutillib.commands.GiveItemCommand;
import me.andreasmelone.amutillib.utils.CommandUtil;
import me.andreasmelone.amutillib.listeners.ItemEventsListener;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class AMUtilLib {
    public static final AMUtilLib INSTANCE = new AMUtilLib();

    public void registerEvents(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new ItemEventsListener(), plugin);
        //plugin.getServer().getPluginManager().registerEvents(new CommandEventsListener(plugin), plugin);
    }

    @Deprecated
    private void registerCommands() {
        CommandMap commandMap = CommandUtil.getCommandMap();
        if(commandMap == null) return;
        if(commandMap.getCommand("giveitem") != null) return;

        CommandUtil.registerCommands(CommandUtil.toCommand(new GiveItemCommand(null), "giveitem"));
    }

    public void registerCommands(JavaPlugin plugin, String name) {
        PluginCommand command = plugin.getCommand(name);
        if(command == null) {
            plugin.getLogger().severe("[AMUtilLib] Could not register command " + name + "!");
            return;
        }

        TabExecutor executor = new GiveItemCommand(plugin);
        command.setExecutor(executor);
        command.setTabCompleter(executor);
    }

    public static AMUtilLib getInstance() {
        return INSTANCE;
    }
}
