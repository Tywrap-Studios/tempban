package org.tywrapstudios.tempban

import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class Tempban : JavaPlugin() {
    val token: String = File(dataFolder, "token").readText(Charsets.UTF_8)

    override fun onEnable() {
        // Plugin startup logic
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
