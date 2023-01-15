package com.vjh0107.proxysynchronizer.inventory

import org.bukkit.entity.Player
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.Module

@Module
class SyncPlayerInventoryDataFactory {
    @Factory
    fun provideSyncPlayerInventoryData(@InjectedParam player: Player): SyncPlayerInventoryData {
        return player.inventory.run {
            SyncPlayerInventoryData(
                storageContents.toMutableList(),
                armorContents.toMutableList(),
                extraContents.toMutableList()
            )
        }
    }
}