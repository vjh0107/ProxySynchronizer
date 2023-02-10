package com.vjh0107.proxysynchronizer.enderchest.service.impl

import com.vjh0107.barcode.framework.koin.injector.inject
import com.vjh0107.proxysynchronizer.enderchest.SyncPlayerEnderChestData
import com.vjh0107.proxysynchronizer.enderchest.repository.SyncPlayerEnderChestRepository
import com.vjh0107.proxysynchronizer.enderchest.service.SyncPlayerEnderChestService
import org.bukkit.entity.Player
import org.koin.core.annotation.Single
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

@Single(binds = [SyncPlayerEnderChestService::class])
class SyncPlayerEnderChestServiceImpl(private val repository: SyncPlayerEnderChestRepository) : SyncPlayerEnderChestService {
    override fun loadSyncPlayerData(player: Player) {
        loadSyncPlayerData(player, repository.getSyncPlayerData(player))
    }

    override fun loadSyncPlayerData(player: Player, data: SyncPlayerEnderChestData) {
        player.enderChest.contents = data.contents.toTypedArray()
    }

    override fun saveSyncPlayerData(player: Player) {
        val data: SyncPlayerEnderChestData by inject(named("player")) { parametersOf(player) }
        repository.setSyncPlayerData(player, data)
    }
}