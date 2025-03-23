@file:Suppress("UnstableApiUsage")

package org.tywrapstudios.tempban.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.arguments.StringArgumentType
import io.papermc.paper.command.brigadier.CommandSourceStack
import io.papermc.paper.command.brigadier.Commands
import io.papermc.paper.command.brigadier.argument.ArgumentTypes

object CommandImpl  {
    fun init(dispatcher: CommandDispatcher<CommandSourceStack>) {
        val tempban = Commands
            .literal("tempban")
            .requires { sender -> sender.sender.hasPermission("permission.tempban") }.build()

        val userArg = Commands
            .argument("targets", ArgumentTypes.playerProfiles()).build()

        val monthsArg = Commands
            .argument("months", IntegerArgumentType.integer(0)).build()

        val daysArg = Commands
            .argument("days", IntegerArgumentType.integer(0)).build()

        val hoursArg = Commands
            .argument("hours", IntegerArgumentType.integer(0))
            .executes{ ctx -> CommandExecutables.tempbanPlayers(ctx, null) }.build()

        val reasonArg = Commands
            .argument("reason", StringArgumentType.greedyString())
            .executes{ ctx -> CommandExecutables.tempbanPlayers(ctx, StringArgumentType.getString(ctx, "reason")) }.build()


        /* Root */
        dispatcher.root.addChild(tempban)
        /* Arguments */
        tempban.addChild(userArg)
        userArg.addChild(monthsArg)
        monthsArg.addChild(daysArg)
        daysArg.addChild(hoursArg)
        hoursArg.addChild(reasonArg)
    }
}