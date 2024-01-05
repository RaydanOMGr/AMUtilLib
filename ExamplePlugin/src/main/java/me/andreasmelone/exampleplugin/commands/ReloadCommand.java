package me.andreasmelone.exampleplugin.commands;

import me.andreasmelone.exampleplugin.ExamplePlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.List;

public class ReloadCommand implements TabExecutor {
    private final ExamplePlugin plugin;
    public ReloadCommand(ExamplePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        plugin.reloadConfig();
        plugin.config = plugin.getConfig();
        plugin.getI18n().setLanguage(plugin.config.getString("lang"));

        sender.sendMessage(plugin.getI18n().getTransformed("command.reload"));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return new ArrayList<>();
    }
}
