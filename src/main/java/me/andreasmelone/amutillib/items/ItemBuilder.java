package me.andreasmelone.amutillib.items;

import org.bukkit.Material;

public class ItemBuilder {
    private String id;
    private String name;
    private String[] lore;
    private Material material;

    public ItemBuilder setId(String id) {
        this.id = id;
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

    public AMItem build() {
        if(id == null) throw new IllegalArgumentException("id must not be null");
        if(material == null) throw new IllegalArgumentException("material must not be null");

        return new AMItem(id, name, lore, material);
    }
}
