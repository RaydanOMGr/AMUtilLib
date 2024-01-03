package me.andreasmelone.amutillib.items;

import me.andreasmelone.amutillib.items.events.OnBlockBreakRunnable;
import me.andreasmelone.amutillib.items.events.OnInteractRunnable;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemBuilder {
    private NamespacedKey key;
    private String name;
    private String[] lore;
    private Material material;
    private int customModelData = -1;
    private OnInteractRunnable onInteract;
    private OnBlockBreakRunnable onBlockBreak;

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

    public ItemBuilder onInteract(OnInteractRunnable onInteract) {
        this.onInteract = onInteract;
        return this;
    }

    public ItemBuilder onBlockBreak(OnBlockBreakRunnable onBlockBreak) {
        this.onBlockBreak = onBlockBreak;
        return this;
    }

    public AMItem build() {
        if(key == null) throw new IllegalArgumentException("id must not be null");
        if(material == null) throw new IllegalArgumentException("material must not be null");

        if(onInteract == null) onInteract = (event) -> {};
        if(onBlockBreak == null) onBlockBreak = (event) -> {};

        return new AMItem(key, name, lore, material, customModelData,
                onInteract, onBlockBreak);
    }

    public static ItemBuilder createBuilder(JavaPlugin plugin, String id) {
        return new ItemBuilder().setKey(new NamespacedKey(plugin, id));
    }
}