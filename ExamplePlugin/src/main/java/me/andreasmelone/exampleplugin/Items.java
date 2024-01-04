package me.andreasmelone.exampleplugin;

import me.andreasmelone.amutillib.items.AMItem;
import me.andreasmelone.amutillib.items.ItemBuilder;
import me.andreasmelone.amutillib.registry.ItemRegister;
import me.andreasmelone.amutillib.registry.RegisteredObject;
import org.bukkit.Material;

public class Items {
    static ExamplePlugin plugin = ExamplePlugin.getInstance();
    public static RegisteredObject<AMItem> exampleItem = ItemRegister.getInstance().register(
            ItemBuilder.createBuilder(plugin, "example_item")
                    .setName("Example Item")
                    .setLore("This is an example item.")
                    .setMaterial(Material.STONE_SHOVEL)
                    .build()
    );

    public static void register() {
        exampleItem.get().onInteract((event) -> {
            if(event.getAction().toString().contains("RIGHT"))
                event.getPlayer().sendMessage("You clicked the example item!");
        });
        exampleItem.get().onBlockBreak((event) -> {
            event.getPlayer().sendMessage("You broke a block with the example item!");
        });
    }
}
