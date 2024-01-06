package me.andreasmelone.amutillib.items;

import me.andreasmelone.amutillib.registry.Register;
import me.andreasmelone.amutillib.registry.RegisteredObject;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class ItemRegister extends Register<AMItem> {
    private static final ItemRegister INSTANCE = new ItemRegister();
    private ItemRegister() {}

    public static ItemRegister getInstance() {
        return INSTANCE;
    }
}
