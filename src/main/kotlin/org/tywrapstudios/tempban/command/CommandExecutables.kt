@file:Suppress("UnstableApiUsage")

package org.tywrapstudios.tempban.command

import com.destroystokyo.paper.profile.PlayerProfile
import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.context.CommandContext
import io.papermc.paper.ban.BanListType
import io.papermc.paper.command.brigadier.CommandSourceStack
import io.papermc.paper.command.brigadier.argument.resolvers.PlayerProfileListResolver
import net.kyori.adventure.text.Component
import org.apache.commons.lang3.time.DateUtils
import java.util.Date

object CommandExecutables {

    fun tempbanPlayers(ctx: CommandContext<CommandSourceStack>, reason: String?): Int {
        return tempbanPlayers(ctx.source, ctx.getArgument("targets", PlayerProfileListResolver::class.java).resolve(ctx.source), IntegerArgumentType.getInteger(ctx, "months"), IntegerArgumentType.getInteger(ctx, "days"), IntegerArgumentType.getInteger(ctx, "hours"), reason)
    }

    private fun tempbanPlayers(source: CommandSourceStack, offenders: Collection<PlayerProfile>, months: Int, days: Int, hours: Int, reason: String?): Int {
        val banlist = source.sender.server.getBanList(BanListType.PROFILE)
        var i = 0
        val date = DateUtils.addMonths(DateUtils.addDays(DateUtils.addHours(Date(), hours), days), months)

        for (offender in offenders) {
            banlist.addBan(offender, reason, date, source.sender.name)
            ++i
            source.sender.sendMessage(Component.translatable("command.tempban.success", Component.text(offender.name!!), Component.text(months), Component.text(days), Component.text(hours)))

            val player = source.sender.server.getPlayer(offender.id!!)

            player?.kick(Component.translatable("multiplayer.disconnect.banned"))
        }

        return i
    }
}