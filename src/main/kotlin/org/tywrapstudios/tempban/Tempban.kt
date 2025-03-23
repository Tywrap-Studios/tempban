@file:Suppress("UnstableApiUsage")

package org.tywrapstudios.tempban

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents
import org.bukkit.plugin.java.JavaPlugin
import org.tywrapstudios.tempban.api.bandb.ApiClient
import org.tywrapstudios.tempban.command.CommandImpl
import java.io.File

class Tempban : JavaPlugin() {
    var client: ApiClient? = null

    override fun onEnable() {
        this.lifecycleManager.registerEventHandler(LifecycleEvents.COMMANDS) {
            commands -> CommandImpl.init(commands.registrar().dispatcher)
        }
        
        saveDefaultConfig()

        client = ApiClient(config.getString("api-client.token")!!, config.getString("api-client.address")!!)
    }
}
