package me.andreasmelone.amutillib.commands;

import me.andreasmelone.amutillib.AMUtilLib;
import me.andreasmelone.amutillib.items.AMItem;
import me.andreasmelone.amutillib.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GiveItemCommand implements TabExecutor {
    private final AMUtilLib lib;
    private final String pluginNamespaceName;
    public GiveItemCommand(AMUtilLib lib, JavaPlugin plugin) {
        this(lib, plugin.getName().toLowerCase());
    }

    public GiveItemCommand(AMUtilLib lib, String namespace) {
        this.lib = lib;
        this.pluginNamespaceName = namespace;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length < 2) {
            sender.sendMessage(Util.transform("&cUsage: /" + label + " <player> <item>"));
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

        AMItem item = lib.getItemRegister().getRegisteredElements().get(NamespacedKey.fromString(itemName));
        if(item == null) {
            sender.sendMessage(Util.transform("&cItem not found!"));
            return true;
        }

        int amount = 1;
        if(args.length > 2) {
            try {
                amount = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                sender.sendMessage(Util.transform("&cAmount must be a number!"));
                return true;
            }
        }

        for(Player target : targets) {
            ItemStack itemStack = item.createItemStack();
            itemStack.setAmount(amount);
            target.getInventory().addItem(itemStack);
            target.sendMessage(Util.transform("&aYou received *" + amount + " [" + item.getName() + "&a]"));
        }

        sender.sendMessage(Util.transform("&aYou gave *" + amount + " [" + item.getName() + "&a] to " + targets.length + " players"));

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> tabComplete = new LinkedList<>();
        if(args.length == 1) {
            tabComplete.add("*");
            tabComplete.addAll(Util.getPlayersWithArgument(args[0]));
        } else if(args.length == 2) {
            List<String> items = lib.getItemRegister().getRegisteredElements()
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
        } else if(args.length == 3) {
            for(int i = 1; i < 9; i++) {
                tabComplete.add(String.valueOf(i));
            }
        }

        return tabComplete;
    }
}
