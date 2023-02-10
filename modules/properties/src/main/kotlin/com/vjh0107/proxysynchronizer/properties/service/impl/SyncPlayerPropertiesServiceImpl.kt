package com.vjh0107.proxysynchronizer.properties.service.impl

import com.vjh0107.barcode.framework.koin.injector.inject
import com.vjh0107.proxysynchronizer.properties.SyncPlayerPropertiesData
import com.vjh0107.proxysynchronizer.properties.repository.SyncPlayerPropertiesRepository
import com.vjh0107.proxysynchronizer.properties.service.SyncPlayerPropertiesService
import org.bukkit.entity.Player
import org.koin.core.annotation.Single
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

@Single(binds = [SyncPlayerPropertiesService::class])
class SyncPlayerPropertiesServiceImpl(
    private val repository: SyncPlayerPropertiesRepository
) : SyncPlayerPropertiesService {
    override fun loadSyncPlayerData(player: Player) {
        loadSyncPlayerData(player, repository.getSyncPlayerData(player))
    }

    override fun loadSyncPlayerData(player: Player, data: SyncPlayerPropertiesData) {
        player.activePotionEffects.forEach { potionEffect -> player.removePotionEffect(potionEffect.type) }

        with(data) {
            player.health = health
            player.gameMode = gameMode
            cooldowns.forEach { (material, ticks) ->
                player.setCooldown(material, ticks)
            }
            player.inventory.heldItemSlot = heldItemSlot
            player.addPotionEffects(potions)
        }
    }

    override fun saveSyncPlayerData(player: Player) {
        val data: SyncPlayerPropertiesData by inject(named("player")) { parametersOf(player) }
        repository.setSyncPlayerData(player, data)
    }
}