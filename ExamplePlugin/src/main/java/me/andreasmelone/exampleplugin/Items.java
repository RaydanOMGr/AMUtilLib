package me.andreasmelone.exampleplugin;

import me.andreasmelone.amutillib.i18n.I18n;
import me.andreasmelone.amutillib.i18n.TranslationKey;
import me.andreasmelone.amutillib.items.AMItem;
import me.andreasmelone.amutillib.items.ItemBuilder;
import me.andreasmelone.amutillib.registry.ItemRegister;
import me.andreasmelone.amutillib.registry.RegisteredObject;
import me.andreasmelone.amutillib.utils.Util;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

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
        // EXAMPLE_ITEM START
        exampleItem.get().onCreateItemStack(event -> {
            event.getItemStack().addEnchantment(Enchantment.DIG_SPEED, 3);

            ItemMeta meta = event.getItemStack().getItemMeta();
            meta.setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            event.getItemStack().setItemMeta(meta);
        });

        exampleItem.get().onInteract((event) -> {
            if(event.getAction().toString().contains("RIGHT"))
                event.getPlayer().sendMessage(plugin.getI18n().getTransformed("example_item.interact"));
        });

        exampleItem.get().onBlockBreak((event) -> {
            event.getPlayer().sendMessage(
                    plugin.getI18n().getTransformed(
                            "example_item.block_break",
                            TranslationKey.of("%block%", event.getBlock().getType().toString())
                    )
            );
        });

        exampleItem.get().onEntityHit((event) -> {
            event.getDamager().sendMessage(
                    plugin.getI18n().getTransformed(
                            "example_item.entity_hit",
                            TranslationKey.of("%entity%", event.getEntity().getType().toString())
                    )
            );
        });
        // EXAMPLE_ITEM END
    }
}
