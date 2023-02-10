package com.vjh0107.proxysynchronizer.properties.listener.impl

import com.vjh0107.barcode.framework.AbstractBarcodePlugin
import com.vjh0107.barcode.framework.component.BarcodeComponent
import com.vjh0107.barcode.framework.component.BarcodeListener
import com.vjh0107.barcode.framework.coroutine.MinecraftMain
import com.vjh0107.barcode.framework.database.player.events.BarcodeRepositoryDataLoadEvent
import com.vjh0107.barcode.framework.database.player.events.BarcodeRepositoryPreSaveEvent
import com.vjh0107.proxysynchronizer.core.service.SyncPlayerService
import com.vjh0107.proxysynchronizer.properties.SyncPlayerPropertiesData
import com.vjh0107.proxysynchronizer.properties.listener.SyncPlayerPropertiesListener
import com.vjh0107.proxysynchronizer.properties.service.SyncPlayerPropertiesService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerQuitEvent
import kotlin.coroutines.CoroutineContext

@BarcodeComponent
class SyncPlayerPropertiesListenerImpl (
    private val plugin: AbstractBarcodePlugin,
    private val service: SyncPlayerPropertiesService
) : BarcodeListener, SyncPlayerPropertiesListener {

    @EventHandler
    override fun onInventoryLoad(event: BarcodeRepositoryDataLoadEvent) {
        Bukkit.getScheduler().runTask(plugin) { _ ->
            val data = event.playerData
            if (data is SyncPlayerPropertiesData) {
                service.loadSyncPlayerData(event.player, data)
            }
        }
    }

    @EventHandler
    override fun onPlayerRepositoryPreSave(event: BarcodeRepositoryPreSaveEvent) {
        service.saveSyncPlayerData(event.player)
    }
}