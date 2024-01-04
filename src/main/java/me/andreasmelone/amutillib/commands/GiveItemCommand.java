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

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GiveItemCommand implements TabExecutor {
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

        AMItem item = ItemRegister.getInstance().getRegisteredItems().get(NamespacedKey.fromString(args[1]));
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
                    .map(NamespacedKey::toString)
                    .collect(Collectors.toCollection(LinkedList::new));
            items = Util.getElementsWithArgument(items, args[1]);
            tabComplete.addAll(items);
        }

        return tabComplete;
    }
}
