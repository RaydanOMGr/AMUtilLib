package me.andreasmelone.amutillib.registry;

import me.andreasmelone.amutillib.items.AMItem;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class ItemRegister {
    private static final ItemRegister INSTANCE = new ItemRegister();

    private final HashMap<NamespacedKey, AMItem> registeredItems = new HashMap<>();

    public RegisteredObject<AMItem> register(AMItem item) {
        registeredItems.put(item.getKey(), item);
        return new RegisteredObject<>(item);
    }

    public HashMap<NamespacedKey, AMItem> getRegisteredItems() {
        return registeredItems;
    }

    public HashMap<String, AMItem> getRegisteredItems(JavaPlugin plugin) {
        HashMap<String, AMItem> items = new HashMap<>();
        for(AMItem item : registeredItems.values()) {
            if(item.getKey().getNamespace().equals(plugin.getName())) {
                items.put(item.getKey().getKey(), item);
            }
        }
        return items;
    }

    public static ItemRegister getInstance() {
        return INSTANCE;
    }
}
