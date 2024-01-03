package me.andreasmelone.amutillib;

import me.andreasmelone.amutillib.registry.Registrable;
import me.andreasmelone.amutillib.registry.Registry;
import me.andreasmelone.amutillib.registry.RegistryObject;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedList;
import java.util.List;

public class PluginRegister {
    private final AMUtilLib amUtilLib;
    private final JavaPlugin plugin;

    protected PluginRegister(JavaPlugin plugin) {
        this.plugin = plugin;
        this.amUtilLib = AMUtilLib.getInstance();
    }

    public <T extends Registrable> Registry<T> createRegistry() {
        Registry<T> registry = Registry.create(plugin.getName());
        amUtilLib.registries.get(this).add(registry);
        return registry;
    }
}
