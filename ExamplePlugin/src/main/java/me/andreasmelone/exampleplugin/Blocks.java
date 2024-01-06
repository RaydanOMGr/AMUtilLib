package me.andreasmelone.exampleplugin;

import me.andreasmelone.amutillib.blocks.AMBlock;
import me.andreasmelone.amutillib.blocks.BlockRegister;
import me.andreasmelone.amutillib.i18n.TranslationKey;
import me.andreasmelone.amutillib.registry.RegisteredObject;

public class Blocks {
    static ExamplePlugin plugin = ExamplePlugin.getInstance();

    public static final RegisteredObject<AMBlock> exampleBlock = BlockRegister.getInstance().register(
            AMBlock.from(Items.exampleBlockItem.get())
    );

    public static void register() {
        // EXAMPLE_BLOCK START
        exampleBlock.get().onBlockPlace(event -> {
            plugin.getLogger().info(plugin.getI18n().getTransformed(
                    "example_block.placed",
                    TranslationKey.of("%block%", exampleBlock.get().getName())
            ));
        });

        exampleBlock.get().onInteract(event -> {
            plugin.getLogger().info(plugin.getI18n().getTransformed(
                    "example_block.interacted",
                    TranslationKey.of("%block%", exampleBlock.get().getName())
            ));
        });
        // EXAMPLE_BLOCK END
    }
}
