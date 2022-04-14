package com.brakezap

import org.bukkit.Bukkit
import org.bukkit.Location
import redempt.redlib.region.CuboidRegion

class RegionSaver {

    companion object{
        fun saveRegions(){
            val regions = KotlinPlugin.getRegionList()
            if (regions.isEmpty()) return
            val stringRegions = regions.map{r -> r.toString()}
            val config = KotlinPlugin.getMainPlugin().config
            config.set("Number of regions", regions.size)
            for ((i, region) in stringRegions.withIndex()){
                config.set("Region $i", region)
            }
            KotlinPlugin.getMainPlugin().saveConfig()
            }

        fun loadRegions(){
            val config = KotlinPlugin.getMainPlugin().config
            val numOfRegions = config.getInt("Number of regions")
            if (numOfRegions == 0) return
            for (i in 0 until numOfRegions){
                val infoString = config.getString("Region $i")
                val info = infoString?.split(" ")
                val loc1 = info?.get(1)?.toDouble()
                    ?.let { it -> Location(info[0].let { Bukkit.getWorld(it) }, it, info[2].toDouble(), info[3].toDouble()) }
                val loc2 = info?.get(4)?.toDouble()
                    ?.let { it -> Location(info[0].let { Bukkit.getWorld(it) }, it, info[5].toDouble(), info[6].toDouble()) }
                KotlinPlugin.getRegionList().add(CuboidRegion(loc1, loc2))
            }
        }
        }
}