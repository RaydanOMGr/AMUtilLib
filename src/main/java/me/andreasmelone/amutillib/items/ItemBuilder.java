package me.andreasmelone.amutillib.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemBuilder {
    private NamespacedKey key;
    private String name;
    private String[] lore;
    private Material material;
    private int customModelData = -1;

    public ItemBuilder setKey(JavaPlugin plugin, String id) {
        return setKey(new NamespacedKey(plugin, id));
    }

    public ItemBuilder setKey(NamespacedKey key) {
        this.key = key;
        return this;
    }

    public ItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder setLore(String[] lore) {
        this.lore = lore;
        return this;
    }

    public ItemBuilder setLore(String lore) {
        this.lore = lore.split("\n");
        return this;
    }

    public ItemBuilder setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public ItemBuilder setCustomModelData(int customModelData) {
        this.customModelData = customModelData;
        return this;
    }

    public AMItem build() {
        if(key == null) throw new IllegalArgumentException("id must not be null");
        if(material == null) throw new IllegalArgumentException("material must not be null");

        return new AMItem(key, name, lore, material, customModelData);
    }

    public static ItemBuilder create(JavaPlugin plugin, String id) {
        return new ItemBuilder().setKey(new NamespacedKey(plugin, id));
    }
}