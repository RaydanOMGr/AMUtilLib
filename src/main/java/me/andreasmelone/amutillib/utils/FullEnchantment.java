package me.andreasmelone.amutillib.utils;

import org.bukkit.enchantments.Enchantment;

public interface FullEnchantment {
    Enchantment getEnchantment();
    int getLevel();

    static FullEnchantment of(Enchantment enchantment, int level) {
        return new FullEnchantment() {
            @Override
            public Enchantment getEnchantment() {
                return enchantment;
            }

            @Override
            public int getLevel() {
                return level;
            }
        };
    }
}
