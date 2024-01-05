package me.andreasmelone.amutillib.commands;

import me.andreasmelone.amutillib.items.AMItem;
import me.andreasmelone.amutillib.registry.ItemRegister;
import me.andreasmelone.amutillib.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GiveItemCommand implements TabExecutor {
    private final JavaPlugin plugin;
    private final String pluginNamespaceName;
    public GiveItemCommand(JavaPlugin plugin) {
        this.plugin = plugin;
        this.pluginNamespaceName = new NamespacedKey(plugin, plugin.getName().toLowerCase())
                .toString()
                .split(":")[0];
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length < 2) {
            sender.sendMessage(Util.transform("§cUsage: /" + label + " <player> <item>"));
            return true;
        }

        Player[] targets;
        if(args[0].equals("*")) {
            targets = new Player[Bukkit.getOnlinePlayers().size()];
            targets = Bukkit.getOnlinePlayers().toArray(targets);
        } else {
            targets = new Player[1];
            targets[0] = Bukkit.getPlayer(args[0]);
        }

        String itemName = args[1];
        if(itemName.split(":").length < 2) {
            itemName = pluginNamespaceName + ":" + itemName;
        }

        AMItem item = ItemRegister.getInstance().getRegisteredItems().get(NamespacedKey.fromString(itemName));
        if(item == null) {
            sender.sendMessage(Util.transform("§cItem not found!"));
            return true;
        }

        for(Player target : targets) {
            target.getInventory().addItem(item.createItemStack());
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> tabComplete = new LinkedList<>();
        if(args.length == 1) {
            tabComplete.add("*");
            tabComplete.addAll(Util.getPlayersWithArgument(args[0]));
        }
        if(args.length == 2) {
            List<String> items = ItemRegister.getInstance().getRegisteredItems()
                    .keySet()
                    .stream()
                    .map(key -> {
                        String result;
                        String[] split = key.toString().split(":");
                        if(split[0].equals(pluginNamespaceName)) {
                            result = split[1];
                        } else {
                            result = key.toString();
                        }
                        return result;
                    })
                    .collect(Collectors.toCollection(LinkedList::new));
            items = Util.getElementsWithArgument(items, args[1]);
            tabComplete.addAll(items);
        }

        return tabComplete;
    }
}
