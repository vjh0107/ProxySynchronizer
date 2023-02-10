package com.vjh0107.proxysynchronizer.inventory.listener.impl

import com.vjh0107.barcode.framework.component.BarcodeComponent
import com.vjh0107.barcode.framework.component.BarcodeListener
import com.vjh0107.barcode.framework.database.player.events.BarcodeRepositoryDataLoadEvent
import com.vjh0107.barcode.framework.database.player.events.BarcodeRepositoryPreSaveEvent
import com.vjh0107.proxysynchronizer.inventory.SyncPlayerInventoryData
import com.vjh0107.proxysynchronizer.inventory.listener.SyncPlayerInventoryListener
import com.vjh0107.proxysynchronizer.inventory.service.SyncPlayerInventoryService
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerQuitEvent

@BarcodeComponent
class SyncPlayerInventoryListenerImpl(
    private val service: SyncPlayerInventoryService
) : BarcodeListener, SyncPlayerInventoryListener {
    @EventHandler
    override fun onInventoryLoad(event: BarcodeRepositoryDataLoadEvent) {
        val data = event.playerData
        if (data is SyncPlayerInventoryData) {
            service.loadSyncPlayerData(event.player, data)
        }
    }

    @EventHandler
    override fun onPlayerRepositoryPreSave(event: BarcodeRepositoryPreSaveEvent) {
        service.saveSyncPlayerData(event.player)
    }

    @EventHandler
    override fun onPlayerQuit(event: PlayerQuitEvent) {
        event.player.inventory.clear()
    }
}