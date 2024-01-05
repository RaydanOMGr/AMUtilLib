package me.andreasmelone.amutillib.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class CommandUtil {
    public static void registerCommands(Command... commands) {
        CommandMap commandMap = getCommandMap();

        // Register all the commands into the map
        if(commandMap == null) {
            Bukkit.getLogger().warning("Could not register commands as CommandMap is null!");
            return;
        }
        for (final Command command : commands) {
            boolean registered = commandMap.register("amutillib", command);
            if(!registered) {
                Bukkit.getLogger().warning("Could not register command " + command.getName() + "!");
            }
        }
    }

    public static CommandMap getCommandMap() {
        Field commandMapField = null;
        try {
            commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            return (CommandMap) commandMapField.get(Bukkit.getServer());
        } catch (final Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static Command toCommand(CommandExecutor executor, String name, String... aliases) {
        if(aliases.length == 0) {
            aliases = new String[] { name };
        }
        Command command = new Command(name) {
            @Override
            public boolean execute(CommandSender sender, String commandLabel, String[] args) {
                return executor.onCommand(sender, this, commandLabel, args);
            }

            @Nonnull
            @Override
            public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
                if(!(executor instanceof TabExecutor)) {
                    return super.tabComplete(sender, alias, args);
                }
                List<String> tabComplete = ((TabExecutor) executor).onTabComplete(sender, this, alias, args);
                if(tabComplete == null) {
                    return super.tabComplete(sender, alias, args);
                }
                return tabComplete;
            }
        };
        command.setAliases(Arrays.asList(aliases));
        return command;
    }

    public static void registerTabExecutor(JavaPlugin plugin, TabExecutor executor, String name) {
        plugin.getCommand(name).setExecutor(executor);
        plugin.getCommand(name).setTabCompleter(executor);
    }
}
