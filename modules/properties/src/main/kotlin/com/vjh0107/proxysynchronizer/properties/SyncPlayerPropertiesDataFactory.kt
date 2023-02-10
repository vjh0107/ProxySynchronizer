package com.vjh0107.proxysynchronizer.properties

import com.vjh0107.barcode.framework.nms.NMSService
import com.vjh0107.barcode.framework.serialization.deserialize
import com.vjh0107.proxysynchronizer.properties.entity.SyncPlayerPropertiesEntity
import com.vjh0107.proxysynchronizer.properties.util.PotionEffectSerializer
import kotlinx.serialization.builtins.ListSerializer
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.Player
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named

@Module
class SyncPlayerPropertiesDataFactory {
    @Factory
    @Named("player")
    fun provideSyncPlayerPropertiesDataByPlayer(@InjectedParam player: Player, nmsService: NMSService): SyncPlayerPropertiesData {
        return SyncPlayerPropertiesData(
            player.health,
            player.gameMode,
            nmsService.getAllCooldowns(player),
            player.inventory.heldItemSlot,
            player.activePotionEffects.toList()
        )
    }

    @Factory
    @Named("default")
    fun provideSyncPlayerPropertiesSDataDefault(@InjectedParam player: Player): SyncPlayerPropertiesData {
        return SyncPlayerPropertiesData(player.health, player.gameMode, mapOf(), player.inventory.heldItemSlot, listOf())
    }

    @Factory
    @Named("entity")
    fun provideSyncPlayerPropertiesDataByEntity(@InjectedParam entity: SyncPlayerPropertiesEntity): SyncPlayerPropertiesData {
        val health = entity.health
        val gameMode = GameMode.valueOf(entity.gameMode)
        val cooldowns = entity.cooldowns.deserialize<Map<Material, Int>>()
        val heldItemSlot = entity.heldItemSlot
        val potions = entity.potions.deserialize(ListSerializer(PotionEffectSerializer))
        return SyncPlayerPropertiesData(health, gameMode, cooldowns, heldItemSlot, potions)
    }
}