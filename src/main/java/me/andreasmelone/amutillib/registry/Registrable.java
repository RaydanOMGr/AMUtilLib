package me.andreasmelone.amutillib.registry;

import org.bukkit.NamespacedKey;

import javax.annotation.Nonnull;

public interface Registrable {
    @Nonnull
    NamespacedKey getKey();
}
