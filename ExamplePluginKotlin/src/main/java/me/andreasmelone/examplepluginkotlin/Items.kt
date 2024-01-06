package me.andreasmelone.examplepluginkotlin

import me.andreasmelone.amutillib.i18n.TranslationKey
import me.andreasmelone.amutillib.items.AMItem
import me.andreasmelone.amutillib.items.ItemBuilder
import me.andreasmelone.amutillib.items.ItemRegister
import me.andreasmelone.amutillib.items.events.CreateItemStackEvent
import me.andreasmelone.amutillib.registry.RegisteredObject
import me.andreasmelone.amutillib.utils.Util
import me.andreasmelone.exampleplugin.Items
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemFlag
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Items {
    private val plugin: ExamplePluginKotlin = ExamplePluginKotlin.getPlugin()

    companion object {
        private val plugin: ExamplePluginKotlin = ExamplePluginKotlin.getPlugin()
        val exampleItem: RegisteredObject<AMItem> = ItemRegister.getInstance().register(
                ItemBuilder.createBuilder(plugin, "example_item")
                        .setName("&rExample Item")
                        .setLore("&rThis is an example item.")
                        .setMaterial(Material.STONE_SHOVEL)
                        .build()
        )

        val shit: RegisteredObject<AMItem> = ItemRegister.getInstance().register(
                ItemBuilder.createBuilder(plugin, "shit")
                        .setName("&rShit")
                        .setLore("&r&fStinks.")
                        .setMaterial(Material.BROWN_DYE)
                        .build()
        )

        fun register() {
            // EXAMPLE_ITEM START
            Items.exampleItem.get().onCreateItemStack { event: CreateItemStackEvent ->
                event.itemStack.addEnchantment(Enchantment.DIG_SPEED, 3)

                val meta = event.itemStack.itemMeta
                meta!!.isUnbreakable = true
                meta!!.addItemFlags(ItemFlag.HIDE_ENCHANTS)
                event.itemStack.setItemMeta(meta)
            }

            Items.exampleItem.get().onInteract { event: PlayerInteractEvent ->
                if (Util.isRightClick(event.action)) {
                    if (event.clickedBlock != null) event.player.sendMessage(plugin.getI18n().getTransformed(
                            "example_item.interact",
                            TranslationKey.of("%block%", Util.nameFromType(event.clickedBlock!!.type))
                    ))
                    else event.player.sendMessage(plugin.getI18n().getTransformed(
                            "example_item.interact_nothing"
                    ))
                }
            }

            Items.exampleItem.get().onBlockBreak { event: BlockBreakEvent ->
                event.player.sendMessage(plugin.getI18n().getTransformed(
                        "example_item.block_break",
                        TranslationKey.of("%block%", Util.nameFromType(event.block.type))
                ))
            }

            Items.exampleItem.get().onEntityHit { event: EntityDamageByEntityEvent ->
                event.damager.sendMessage(plugin.getI18n().getTransformed(
                        "example_item.entity_hit",
                        TranslationKey.of("%entity%", Util.nameFromType(event.entity.type))
                ))
            }
            // EXAMPLE_ITEM END

            // SHIT START
            Items.shitItem.get().onInteract { event: PlayerInteractEvent ->
                val player = event.player
                val amount = event.item!!.amount
                if (amount == 1) player.inventory.setItem(event.hand!!, null)
                else event.item!!.amount = amount - 1

                player.sendMessage(plugin.getI18n().getTransformed("shit.eaten"))
                val nausea = PotionEffect(PotionEffectType.CONFUSION, 10 * 20, 1)
                player.addPotionEffect(nausea)
            }
            // SHIT END
        }
    }
}