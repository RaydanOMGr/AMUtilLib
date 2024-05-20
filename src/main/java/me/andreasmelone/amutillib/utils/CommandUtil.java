package me.andreasmelone.amutillib.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.command.defaults.BukkitCommand;
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

    public static void registerTabExecutor(JavaPlugin plugin, TabExecutor executor, String name) {
        plugin.getCommand(name).setExecutor(executor);
        plugin.getCommand(name).setTabCompleter(executor);
    }

    public static class AMUtilLibCommand extends Command {
        private final CommandExecutor executor;
        private final TabCompleter completer;

        public AMUtilLibCommand(String name, CommandExecutor executor, TabCompleter completer) {
            super(name);
            this.executor = executor;
            this.completer = completer;
        }

        @Override
        public boolean execute(@Nonnull CommandSender sender, @Nonnull String commandLabel, @Nonnull String[] args) {
            return executor.onCommand(sender, this, commandLabel, args);
        }

        @Override
        public List<String> tabComplete(@Nonnull CommandSender sender, @Nonnull String alias, @Nonnull String[] args) throws IllegalArgumentException {
            List<String> completions = completer.onTabComplete(sender, this, alias, args);
            if(completions == null) {
                return super.tabComplete(sender, alias, args);
            }
            return completions;
        }
    }
}
