package me.andreasmelone.amutillib;

import me.andreasmelone.amutillib.blocks.AMBlock;
import me.andreasmelone.amutillib.commands.GiveItemCommand;
import me.andreasmelone.amutillib.events.register.ServerTickRunnable;
import me.andreasmelone.amutillib.items.AMItem;
import me.andreasmelone.amutillib.listeners.BlockEventsListener;
import me.andreasmelone.amutillib.registry.Register;
import me.andreasmelone.amutillib.utils.CommandUtil;
import me.andreasmelone.amutillib.listeners.ItemEventsListener;
import org.bukkit.command.CommandMap;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class AMUtilLibImpl implements AMUtilLib {
    private final JavaPlugin registeringPlugin;

    private Register<AMItem> itemRegister;
    private Register<AMBlock> blockRegister;

    public AMUtilLibImpl(JavaPlugin registeringPlugin) {
        this.registeringPlugin = registeringPlugin;
    }

    @Override
    public Register<AMItem> getItemRegister() {
        return itemRegister;
    }

    @Override
    public Register<AMBlock> getBlockRegister() {
        return blockRegister;
    }

    @Override
    public JavaPlugin getRegisteringPlugin() {
        return registeringPlugin;
    }

    void registerEvents(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new ItemEventsListener(this), plugin);
        plugin.getServer().getPluginManager().registerEvents(new BlockEventsListener(this), plugin);

        new ServerTickRunnable().runTaskTimer(plugin, 0, 1);
    }

    void registerCommands() {
        CommandMap commandMap = CommandUtil.getCommandMap();
        if(commandMap == null) return;
        if(commandMap.getCommand("giveitem") != null) return;

        TabExecutor giveItemCommand = new GiveItemCommand(this, "amutillib");
        CommandUtil.registerCommands(
                new CommandUtil.AMUtilLibCommand("giveitem", giveItemCommand, giveItemCommand)
        );
    }

    void initRegisters() {
        itemRegister = new Register<>();
        blockRegister = new Register<>();
    }
}
