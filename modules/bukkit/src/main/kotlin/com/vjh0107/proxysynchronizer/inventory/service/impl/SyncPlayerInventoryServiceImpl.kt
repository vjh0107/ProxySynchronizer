package com.vjh0107.proxysynchronizer.inventory.service.impl

import com.vjh0107.barcode.framework.koin.injector.inject
import com.vjh0107.proxysynchronizer.inventory.SyncPlayerInventoryData
import com.vjh0107.proxysynchronizer.inventory.repository.SyncPlayerInventoryRepository
import com.vjh0107.proxysynchronizer.inventory.service.SyncPlayerInventoryService
import org.bukkit.entity.Player
import org.koin.core.annotation.Single
import org.koin.core.parameter.parametersOf

@Single(binds = [SyncPlayerInventoryService::class])
class SyncPlayerInventoryServiceImpl(private val repository: SyncPlayerInventoryRepository) : SyncPlayerInventoryService {
    override fun loadPlayerInventory(player: Player) {
        val data = repository.getPlayerInventoryData(player)
        loadPlayerInventory(player, data)
    }

    override fun loadPlayerInventory(player: Player, data: SyncPlayerInventoryData) {
        with(player.inventory) {
            contents = data.inventory.toTypedArray()
            armorContents = data.armors.toTypedArray()
        }
    }

    override fun savePlayerInventory(player: Player) {
        val data: SyncPlayerInventoryData by inject { parametersOf(player) }
        savePlayerInventory(player, data)
    }

    override fun savePlayerInventory(player: Player, data: SyncPlayerInventoryData) {
        repository.setPlayerInventoryData(player, data)
    }
}