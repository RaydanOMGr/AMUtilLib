package me.andreasmelone.amutillib.blocks;

import me.andreasmelone.amutillib.registry.Register;

public class BlockRegister extends Register<AMBlock> {
    private static final BlockRegister INSTANCE = new BlockRegister();
    private BlockRegister() {}

    public static BlockRegister getInstance() {
        return INSTANCE;
    }
}
