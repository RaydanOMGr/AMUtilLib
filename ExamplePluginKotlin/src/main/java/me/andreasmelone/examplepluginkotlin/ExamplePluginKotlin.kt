package me.andreasmelone.examplepluginkotlin

import me.andreasmelone.amutillib.AMUtilLib
import me.andreasmelone.amutillib.i18n.I18n
import me.andreasmelone.amutillib.utils.CommandUtil
import me.andreasmelone.examplepluginkotlin.commands.ReloadCommand
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.plugin.java.JavaPlugin

class ExamplePluginKotlin: JavaPlugin() {
    private val i18n = I18n(this)
    public var config: FileConfiguration? = null

    override fun onEnable() {
        saveDefaultConfig()
        config = getConfig()

        i18n.language = config!!.getString("language")!!

        Items.register()

        AMUtilLib.getInstance().registerCommands(this, "giveitem")
        AMUtilLib.getInstance().registerEvents(this)

        CommandUtil.registerTabExecutor(this, ReloadCommand(this), "giveitem")
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    fun getI18n(): I18n {
        return i18n
    }

    companion object {
        fun getPlugin(): ExamplePluginKotlin {
            return JavaPlugin.getPlugin(ExamplePluginKotlin::class.java)
        }
    }
}