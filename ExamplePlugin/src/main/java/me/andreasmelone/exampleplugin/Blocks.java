package me.andreasmelone.exampleplugin;

import me.andreasmelone.amutillib.blocks.AMBlock;
import me.andreasmelone.amutillib.registry.RegisteredObject;
import me.andreasmelone.amutillib.utils.Util;

public class Blocks {
    static ExamplePlugin plugin = ExamplePlugin.getInstance();

    public static final RegisteredObject<AMBlock> exampleBlock = plugin.getAMUtilLib().getBlockRegister().register(
            AMBlock.from(Items.exampleBlockItem.get())
    );

    public static void register() {
        // EXAMPLE_BLOCK START
        exampleBlock.get().onBlockPlace(event -> {
            event.getPlayer().sendMessage(plugin.getI18n().getTransformed(
                    "example_block.placed"
            ));
        });

        exampleBlock.get().onInteract(event -> {
            if(Util.isRightClick(event.getAction()))
                event.getPlayer().sendMessage(plugin.getI18n().getTransformed(
                        "example_block.interact"
                ));
        });

        exampleBlock.get().onBlockBreak(event -> {
            event.getPlayer().sendMessage(plugin.getI18n().getTransformed(
                    "example_block.break"
            ));
        });
        // EXAMPLE_BLOCK END
    }
}
