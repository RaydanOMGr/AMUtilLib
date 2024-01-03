package me.andreasmelone.amutillib.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.List;

public class Util {
    public static List<String> getPlayersWithArgument(String argument) {
        List<String> players = new LinkedList<>();
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(player.getName().toLowerCase().startsWith(argument.toLowerCase())) {
                players.add(player.getName());
            }
        }
        return players;
    }

    public static String transform(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
