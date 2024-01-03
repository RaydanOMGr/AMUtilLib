package me.andreasmelone.exampleplugin;

import me.andreasmelone.amutillib.AMUtilLib;
import me.andreasmelone.amutillib.PluginRegister;
import me.andreasmelone.amutillib.items.AMItem;
import me.andreasmelone.amutillib.items.ItemBuilder;
import me.andreasmelone.amutillib.registry.Registry;
import me.andreasmelone.amutillib.registry.RegistryObject;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExamplePlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        PluginRegister pluginRegister = AMUtilLib.getInstance().getRegister(this);
        Registry<AMItem> registry = pluginRegister.createRegistry();
        RegistryObject<AMItem> registryObject = registry.create(
                "example_item",
                new ItemBuilder()
                        .setId("example_item")
                        .setName("Example Item")
                        .setLore("This is an example item")
                        .setMaterial(Material.STONE)
                        .build()
                );
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
