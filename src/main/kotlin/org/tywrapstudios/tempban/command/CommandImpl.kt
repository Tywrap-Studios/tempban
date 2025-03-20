@file:Suppress("UnstableApiUsage")

package org.tywrapstudios.tempban.command

import com.mojang.brigadier.CommandDispatcher
import io.papermc.paper.command.brigadier.CommandSourceStack
import io.papermc.paper.command.brigadier.Commands
import io.papermc.paper.command.brigadier.argument.ArgumentTypes

object CommandImpl  {
    fun init(dispatcher: CommandDispatcher<CommandSourceStack>) {
        val tempban = Commands
            .literal("tempban").build()

        val userArg = Commands
            .argument("user", ArgumentTypes.player()).build()

        dispatcher.root.addChild(tempban)
    }
}

// Example one sec
//fun register(dispatcher: CommandDispatcher<ServerCommandSource?>, access: CommandRegistryAccess?) {
//    val constructraCommand: Unit = CommandManager
//        .literal("constructra")
//        .executes(CaCommandExecutables::execute).build()
//
//    val caCommand: Unit = CommandManager
//        .literal(cc.command_alias)
//        .redirect(constructraCommand).build()
//
//    val nodesCommand: Unit = CommandManager
//        .literal("nodes")
//        .requires { source -> source.hasPermissionLevel(cc.perm_lvl_nodes) }.build()
//
//    val flushCommand: Unit = CommandManager
//        .literal("flush")
//        .requires { source -> source.hasPermissionLevel(cc.perm_lvl_nodes_removal) }
//        .executes(CaCommandExecutables::flushNodes).build()
//
//    val spawnCommand: Unit = CommandManager
//        .literal("spawn").build()
//
//    val posArg: Unit = CommandManager
//        .argument("pos", BlockPosArgumentType.blockPos()).build()
//
//    val typeArg: Unit = CommandManager
//        .argument("type", RegistryEntryReferenceArgumentType.registryEntry(access, CaRegistries.Keys.RESOURCE)).build()
//
//    val obsArg: Unit = CommandManager
//        .argument("obstructed", BoolArgumentType.bool())
//        .executes(CaCommandExecutables::spawnNode).build()
//
//    val purgeCommand: Unit = CommandManager
//        .literal("purge")
//        .requires { source -> source.hasPermissionLevel(cc.perm_lvl_nodes_removal) }.build()
//
//    val posArg2: Unit = CommandManager
//        .argument("pos", BlockPosArgumentType.blockPos())
//        .executes { ctx -> CaCommandExecutables.purgeNode(ctx, false, false) }.build()
//
//    val rangeArg: Unit = CommandManager
//        .argument("range", IntegerArgumentType.integer(0))
//        .executes { ctx -> CaCommandExecutables.purgeNode(ctx, true, false) }.build()
//
//    val removeBlockArg: Unit = CommandManager
//        .argument("destroy_blocks", BoolArgumentType.bool())
//        .executes { ctx -> CaCommandExecutables.purgeNode(ctx, true, true) }.build()
//
//    val removeBlockNoRangeArg: Unit = CommandManager
//        .argument("destroy_blocks", BoolArgumentType.bool())
//        .executes { ctx -> CaCommandExecutables.purgeNode(ctx, false, true) }.build()
//
//    val reloadCommand: Unit = CommandManager
//        .literal("reload")
//        .requires { source -> source.hasPermissionLevel(cc.perm_lvl_reload) }
//        .executes(CaCommandExecutables::reload).build()
//
//    /* Root */
//    dispatcher.getRoot().addChild(constructraCommand)
//    dispatcher.getRoot().addChild(caCommand)
//    /* Reload */
//    constructraCommand.addChild(reloadCommand)
//    /* Nodes */
//    constructraCommand.addChild(nodesCommand)
//    /* Nodes Flush */
//    nodesCommand.addChild(flushCommand)
//    /* Nodes Spawn */
//    nodesCommand.addChild(spawnCommand)
//    spawnCommand.addChild(posArg)
//    posArg.addChild(typeArg)
//    typeArg.addChild(obsArg)
//    /* Nodes Purge */
//    nodesCommand.addChild(purgeCommand)
//    purgeCommand.addChild(posArg2)
//    posArg2.addChild(rangeArg)
//    posArg2.addChild(removeBlockNoRangeArg)
//    rangeArg.addChild(removeBlockArg)
//
//    Util.logInitialisation()
//}