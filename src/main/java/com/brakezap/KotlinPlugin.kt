package com.brakezap

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import redempt.redlib.region.CuboidRegion
import redempt.redlib.region.Region


class KotlinPlugin : JavaPlugin() {
    companion object {
        private var regions = arrayListOf<Region>()
        fun getRegionList() = regions
        private lateinit var instance : KotlinPlugin
        fun getMainPlugin() = instance
    }
    override fun onEnable() {
        instance = this
        Bukkit.getConsoleSender().sendMessage("Plugin enabled!")
        this.saveDefaultConfig()
        RegionSaver.loadRegions()
        Bukkit.getConsoleSender().sendMessage("Regions successfully loaded!")
    }

    override fun onDisable() {
        Bukkit.getConsoleSender().sendMessage("Saving regions...")
        RegionSaver.saveRegions()
        Bukkit.getConsoleSender().sendMessage("Plugin disabled!")
    }


    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            if (label.equals("createRegion", true)) {
                regions.add(CuboidRegion(sender.location, sender.eyeLocation))
                sender.sendMessage(regions[0].toString())
                return true
            }
            if (label.equals("listRegions", true)){
                for (region in regions){
                    sender.sendMessage(region.toString())
                }
            }
        }
        return false
    }
}
