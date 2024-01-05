package me.andreasmelone.amutillib.registry;

import me.andreasmelone.amutillib.items.AMItem;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class ItemRegister {
    private static final ItemRegister INSTANCE = new ItemRegister();

    private final LinkedHashMap<NamespacedKey, RegisteredObject<AMItem>> registeredItems = new LinkedHashMap<>();

    public RegisteredObject<AMItem> register(AMItem item) {
        RegisteredObject<AMItem> registeredObject = new RegisteredObject<>(item);
        registeredItems.put(item.getKey(), registeredObject);
        return registeredObject;
    }

    public HashMap<NamespacedKey, AMItem> getRegisteredItems() {
        HashMap<NamespacedKey, AMItem> items = new HashMap<>();
        for(RegisteredObject<AMItem> regobject : registeredItems.values()) {
            items.put(regobject.get().getKey(), regobject.get());
        }
        return items;
    }

    public HashMap<String, AMItem> getRegisteredItems(JavaPlugin plugin) {
        HashMap<String, AMItem> items = new HashMap<>();
        for(RegisteredObject<AMItem> regobject : registeredItems.values()) {
            AMItem item = regobject.get();
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
