package com.vjh0107.proxysynchronizer.enderchest.listener.impl

import com.vjh0107.barcode.framework.component.BarcodeComponent
import com.vjh0107.barcode.framework.component.BarcodeListener
import com.vjh0107.barcode.framework.database.player.events.BarcodeRepositoryDataLoadEvent
import com.vjh0107.barcode.framework.database.player.events.BarcodeRepositoryPreSaveEvent
import com.vjh0107.proxysynchronizer.enderchest.SyncPlayerEnderChestData
import com.vjh0107.proxysynchronizer.enderchest.listener.SyncPlayerEnderChestListener
import com.vjh0107.proxysynchronizer.enderchest.service.SyncPlayerEnderChestService
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerQuitEvent

@BarcodeComponent
class SyncPlayerEnderChestListenerImpl(
    private val service: SyncPlayerEnderChestService
) : BarcodeListener, SyncPlayerEnderChestListener {
    @EventHandler
    override fun onInventoryLoad(event: BarcodeRepositoryDataLoadEvent) {
        val data = event.playerData
        if (data is SyncPlayerEnderChestData) {
            service.loadSyncPlayerData(event.player, data)
        }
    }

    @EventHandler
    override fun onPlayerRepositoryPreSave(event: BarcodeRepositoryPreSaveEvent) {
        service.saveSyncPlayerData(event.player)
    }

    @EventHandler
    override fun onPlayerQuit(event: PlayerQuitEvent) {
        event.player.enderChest.clear()
    }
}