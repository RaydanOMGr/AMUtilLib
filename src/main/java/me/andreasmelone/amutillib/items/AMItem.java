package me.andreasmelone.amutillib.items;

import me.andreasmelone.amutillib.registry.Registrable;
import org.bukkit.Material;

public class AMItem implements Registrable {
    private final String id;
    private final String name;
    private final String[] lore;
    private final Material material;
    private final int customModelData;

    protected AMItem(String id, String name, String[] lore, Material material,
                     int customModelData) {
        this.id = id;
        this.name = name;
        this.lore = lore;
        this.material = material;
        this.customModelData = customModelData;
    }

    public String getId() {
        return id;
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
}
