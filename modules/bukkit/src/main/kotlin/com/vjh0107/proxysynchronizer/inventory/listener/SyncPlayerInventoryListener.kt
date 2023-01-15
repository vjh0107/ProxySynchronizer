package com.vjh0107.proxysynchronizer.inventory.listener

import com.vjh0107.barcode.framework.component.BarcodeComponent
import com.vjh0107.barcode.framework.component.BarcodeListener
import com.vjh0107.barcode.framework.database.player.events.BarcodePlayerDataLoadEvent
import com.vjh0107.proxysynchronizer.inventory.SyncPlayerInventoryData
import com.vjh0107.proxysynchronizer.inventory.service.SyncPlayerInventoryService
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerQuitEvent

@BarcodeComponent
class SyncPlayerInventoryListener(private val service: SyncPlayerInventoryService) : BarcodeListener {
    @EventHandler
    fun onInventoryLoad(event: BarcodePlayerDataLoadEvent) {
        val data = event.playerData
        if (data is SyncPlayerInventoryData) {
            service.loadPlayerInventory(event.player, data)
        }
    }

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        service.savePlayerInventory(event.player)
        event.player.inventory.clear()
    }
}