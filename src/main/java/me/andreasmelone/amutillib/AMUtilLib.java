package me.andreasmelone.amutillib;

import me.andreasmelone.amutillib.registry.Registry;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AMUtilLib {
    protected final HashMap<JavaPlugin, PluginRegister> pluginRegisters = new HashMap<>();
    protected final HashMap<PluginRegister, List<Registry<?>>> registries = new HashMap<>();
    private static final AMUtilLib INSTANCE = new AMUtilLib();

    private AMUtilLib() {
    }

    public PluginRegister getRegister(JavaPlugin plugin) {
        if (pluginRegisters.containsKey(plugin)) {
            return pluginRegisters.get(plugin);
        }
        PluginRegister pluginRegister = new PluginRegister(plugin);
        pluginRegisters.put(plugin, pluginRegister);
        registries.put(pluginRegister, new LinkedList<>());
        return pluginRegister;
    }

    public static AMUtilLib getInstance() {
        return INSTANCE;
    }
}
