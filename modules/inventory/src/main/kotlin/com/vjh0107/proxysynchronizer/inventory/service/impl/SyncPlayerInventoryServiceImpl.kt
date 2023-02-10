package com.vjh0107.proxysynchronizer.inventory.service.impl

import com.vjh0107.barcode.framework.koin.injector.inject
import com.vjh0107.proxysynchronizer.inventory.SyncPlayerInventoryData
import com.vjh0107.proxysynchronizer.inventory.repository.SyncPlayerInventoryRepository
import com.vjh0107.proxysynchronizer.inventory.service.SyncPlayerInventoryService
import org.bukkit.entity.Player
import org.koin.core.annotation.Single
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

@Single(binds = [SyncPlayerInventoryService::class])
class SyncPlayerInventoryServiceImpl(private val repository: SyncPlayerInventoryRepository) : SyncPlayerInventoryService {
    override fun loadSyncPlayerData(player: Player) {
        loadSyncPlayerData(player, repository.getSyncPlayerData(player))
    }

    override fun loadSyncPlayerData(player: Player, data: SyncPlayerInventoryData) {
        with(player.inventory) {
            storageContents = data.storage.toTypedArray()
            armorContents = data.armors.toTypedArray()
            extraContents = data.extras.toTypedArray()
        }
    }

    override fun saveSyncPlayerData(player: Player) {
        val data: SyncPlayerInventoryData by inject(named("player")) { parametersOf(player) }
        repository.setSyncPlayerData(player, data)
    }
}