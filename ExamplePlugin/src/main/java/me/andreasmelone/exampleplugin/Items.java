package me.andreasmelone.exampleplugin;

import me.andreasmelone.amutillib.i18n.TranslationKey;
import me.andreasmelone.amutillib.items.AMItem;
import me.andreasmelone.amutillib.items.ItemBuilder;
import me.andreasmelone.amutillib.items.ItemRegister;
import me.andreasmelone.amutillib.registry.RegisteredObject;
import me.andreasmelone.amutillib.utils.Util;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Items {
    static ExamplePlugin plugin = ExamplePlugin.getInstance();
    public static RegisteredObject<AMItem> exampleItem = ItemRegister.getInstance().register(
            ItemBuilder.createBuilder(plugin, "example_item")
                    .setName("&rExample Item")
                    .setLore("&rThis is an example item.")
                    .setMaterial(Material.STONE_SHOVEL)
                    .build()
    );

    public static RegisteredObject<AMItem> shitItem = ItemRegister.getInstance().register(
            ItemBuilder.createBuilder(plugin, "shit")
                    .setName("&rShit")
                    .setLore("&r&fStinks.")
                    .setMaterial(Material.BROWN_DYE)
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
            if(Util.isRightClick(event.getAction())) {
                if(event.getClickedBlock() != null)
                    event.getPlayer().sendMessage(plugin.getI18n().getTransformed(
                            "example_item.interact",
                            TranslationKey.of("%block%", Util.nameFromType(event.getClickedBlock().getType()))
                    ));
                else event.getPlayer().sendMessage(plugin.getI18n().getTransformed(
                        "example_item.interact_nothing"
                ));
            }
        });

        exampleItem.get().onBlockBreak((event) -> {
            event.getPlayer().sendMessage(plugin.getI18n().getTransformed(
                    "example_item.block_break",
                    TranslationKey.of("%block%", Util.nameFromType(event.getBlock().getType()))
            ));
        });

        exampleItem.get().onEntityHit((event) -> {
            event.getDamager().sendMessage(plugin.getI18n().getTransformed(
                    "example_item.entity_hit",
                    TranslationKey.of("%entity%", Util.nameFromType(event.getEntity().getType()))
            ));
        });
        // EXAMPLE_ITEM END

        // SHIT START
        shitItem.get().onInteract(event -> {
            Player player = event.getPlayer();

            int amount = event.getItem().getAmount();
            if(amount == 1) player.getInventory().setItem(event.getHand(), null);
            else event.getItem().setAmount(amount -1);

            player.sendMessage(plugin.getI18n().getTransformed("shit.eaten"));
            PotionEffect nausea = new PotionEffect(PotionEffectType.CONFUSION, 10 * 20, 1);
            player.addPotionEffect(nausea);
        });
        // SHIT END
    }
}
