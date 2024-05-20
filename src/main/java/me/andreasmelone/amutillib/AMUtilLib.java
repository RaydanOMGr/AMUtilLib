package me.andreasmelone.amutillib;

import me.andreasmelone.amutillib.blocks.AMBlock;
import me.andreasmelone.amutillib.items.AMItem;
import me.andreasmelone.amutillib.registry.Register;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public interface AMUtilLib {
    /**
     * Returns the item register
     * @return The item register
     */
    Register<AMItem> getItemRegister();

    /**
     * Returns the block register
     * @return The block register
     */
    Register<AMBlock> getBlockRegister();

    /**
     * Returns the plugin that initialized AMUtilLib
     * @return The plugin that initialized AMUtilLib
     */
    JavaPlugin getRegisteringPlugin();

    /**
     * Calls all necessary methods to initialize AMUtilLib
     * <p>
     * This method has to be called for AMUtilLib to be initialized
     * Once initialized, the AMUtilLib instance can be retrieved using
     * {@link AMUtilLib#getInstance(JavaPlugin)}
     * <p>
     * This allows all plugins to work with the same instance of AMUtilLib
     * @param plugin The plugin that is initializing AMUtilLib
     */
    static void register(JavaPlugin plugin) {
        if(plugin.getServer().getServicesManager().isProvidedFor(AMUtilLib.class)) {
            return;
        }

        AMUtilLibImpl amUtilLib = new AMUtilLibImpl(plugin);
        amUtilLib.registerEvents(plugin);
        amUtilLib.registerCommands();
        amUtilLib.initRegisters();

        plugin.getServer().getServicesManager().register(AMUtilLib.class, amUtilLib, plugin, ServicePriority.Normal);
    }

    static AMUtilLib getInstance(JavaPlugin plugin) {
        return plugin.getServer().getServicesManager().load(AMUtilLib.class);
    }
}
