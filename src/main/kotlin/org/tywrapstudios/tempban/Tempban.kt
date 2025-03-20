@file:Suppress("UnstableApiUsage")

package org.tywrapstudios.tempban

import io.papermc.paper.command.brigadier.Commands
import io.papermc.paper.plugin.lifecycle.event.registrar.ReloadableRegistrarEvent
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents
import org.bukkit.plugin.java.JavaPlugin
import org.tywrapstudios.tempban.command.CommandImpl
import java.io.File

class Tempban : JavaPlugin() {
    val token: String = File(dataFolder, "token").readText(Charsets.UTF_8)

    override fun onEnable() {
        this.lifecycleManager.registerEventHandler(LifecycleEvents.COMMANDS) {
            commands: ReloadableRegistrarEvent<Commands> -> CommandImpl.init(commands.registrar().dispatcher)
        }
        // https://docs.papermc.io/paper/dev/command-api/basics/registration
    }

    // TODO Config: https://docs.papermc.io/paper/dev/plugin-configurations
}
