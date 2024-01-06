package me.andreasmelone.examplepluginkotlin.commands

import me.andreasmelone.examplepluginkotlin.ExamplePluginKotlin
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor

class ReloadCommand(private val plugin: ExamplePluginKotlin): TabExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        plugin.reloadConfig()
        plugin.config = plugin.config

        plugin.getI18n().language = plugin.config!!.getString("language")!!

        sender.sendMessage(plugin.getI18n().getTransformed("reload"))
        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>): MutableList<String>? {
        return ArrayList()
    }
}