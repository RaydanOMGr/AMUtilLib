package me.andreasmelone.amutillib.registry;

import me.andreasmelone.amutillib.items.AMItem;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.LinkedHashMap;

public abstract class Register<T extends Registrable> {
    private final LinkedHashMap<NamespacedKey, RegisteredObject<T>> registeredItems = new LinkedHashMap<>();

    public RegisteredObject<T> register(T item) {
        RegisteredObject<T> registeredObject = new RegisteredObject<>(item);
        registeredItems.put(item.getKey(), registeredObject);
        return registeredObject;
    }

    public HashMap<NamespacedKey, T> getRegisteredItems() {
        HashMap<NamespacedKey, T> items = new HashMap<>();
        for(RegisteredObject<T> regobject : registeredItems.values()) {
            items.put(regobject.get().getKey(), regobject.get());
        }
        return items;
    }

    public HashMap<String, T> getRegisteredItems(JavaPlugin plugin) {
        HashMap<String, T> items = new HashMap<>();
        for(RegisteredObject<T> regobject : registeredItems.values()) {
            T item = regobject.get();
            if(item.getKey().getNamespace().equals(plugin.getName())) {
                items.put(item.getKey().getKey(), item);
            }
        }
        return items;
    }
}
