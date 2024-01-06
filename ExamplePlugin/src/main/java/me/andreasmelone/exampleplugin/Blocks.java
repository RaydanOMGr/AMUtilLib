package me.andreasmelone.exampleplugin;

import me.andreasmelone.amutillib.blocks.AMBlock;
import me.andreasmelone.amutillib.blocks.BlockRegister;
import me.andreasmelone.amutillib.registry.RegisteredObject;

public class Blocks {
    static ExamplePlugin plugin = ExamplePlugin.getInstance();

    public static final RegisteredObject<AMBlock> exampleBlock = BlockRegister.getInstance().register(
            AMBlock.from(Items.exampleItem.get())
    );


}
