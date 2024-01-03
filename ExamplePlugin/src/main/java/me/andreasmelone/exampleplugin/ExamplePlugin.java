package me.andreasmelone.exampleplugin;

import me.andreasmelone.amutillib.AMUtilLib;
import me.andreasmelone.amutillib.items.AMItem;
import me.andreasmelone.amutillib.items.ItemBuilder;
import me.andreasmelone.amutillib.registry.ItemRegister;
import me.andreasmelone.amutillib.registry.RegisteredObject;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExamplePlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        RegisteredObject<AMItem> item = ItemRegister.getInstance().register(
                ItemBuilder.createBuilder(this, "example_item")
                        .setName("Example Item")
                        .setLore("This is an example item.")
                        .setMaterial(Material.STONE_SWORD)
                        .onInteract((event) -> {
                            event.getPlayer().sendMessage("You clicked on the " + event.getAMItem().getName() + "!");
                        })
                        .build()
        );

        AMUtilLib.getInstance().registerEvents(this);
        AMUtilLib.getInstance().registerGiveCommand(this, "giveitem");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
