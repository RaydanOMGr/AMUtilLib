package me.andreasmelone.amutillib.items;

import me.andreasmelone.amutillib.items.execute.OnInteractRunnable;
import me.andreasmelone.amutillib.registry.Registrable;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import java.util.Arrays;

public class AMItem implements Registrable {
    private final NamespacedKey key;
    private final String name;
    private final String[] lore;
    private final Material material;
    private final int customModelData;
    private final OnInteractRunnable onInteract;

    protected AMItem(NamespacedKey key, String name, String[] lore, Material material,
                     int customModelData, OnInteractRunnable onInteract) {
        this.key = key;
        this.name = name;
        this.lore = lore;
        this.material = material;
        this.customModelData = customModelData;
        this.onInteract = onInteract;
    }

    @Nonnull
    @Override
    public NamespacedKey getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String[] getLore() {
        return lore;
    }

    public Material getMaterial() {
        return material;
    }

    public int getCustomModelData() {
        return customModelData;
    }

    public OnInteractRunnable getOnInteract() {
        return onInteract;
    }

    public ItemStack createItemStack() {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(
                new NamespacedKey(key.getNamespace(), "item_id"),
                PersistentDataType.STRING,
                key.getKey()
        );

        if(customModelData != -1) meta.setCustomModelData(customModelData);
        if(name != null) meta.setDisplayName(name);
        if(lore != null) meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);
        return item;
    }

    public boolean compareTo(ItemStack item) {
        if(item == null) return false;
        if(item.getType() != material) return false;

        ItemMeta meta = item.getItemMeta();
        if(meta == null) return false;

        PersistentDataContainer container = meta.getPersistentDataContainer();
        String itemId = container.getOrDefault(
                new NamespacedKey(key.getNamespace(), "item_id"),
                PersistentDataType.STRING,
                ""
        );
        if(itemId.isEmpty()) return false;

        return itemId.equals(key.getKey());
    }
}