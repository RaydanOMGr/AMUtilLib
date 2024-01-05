package me.andreasmelone.amutillib.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Util {
    public static List<String> getPlayersWithArgument(String argument) {
        return getElementsWithArgument(Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList()), argument);
    }

    public static List<String> getElementsWithArgument(List<String> elements, String argument) {
        List<String> elementsWithArgument = new LinkedList<>();
        for(String element : elements) {
            if(element.toLowerCase().startsWith(argument.toLowerCase())) {
                elementsWithArgument.add(element);
            }
        }
        return elementsWithArgument;
    }

    public static String nameFromType(Enum<?> blockType) {
        return blockType.toString().toLowerCase().replace("_", " ");
    }

    public static String transform(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
