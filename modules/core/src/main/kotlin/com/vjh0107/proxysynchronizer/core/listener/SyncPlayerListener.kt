package com.vjh0107.proxysynchronizer.core.listener

import com.vjh0107.barcode.framework.database.player.events.BarcodeRepositoryDataLoadEvent
import com.vjh0107.barcode.framework.database.player.events.BarcodeRepositoryPreSaveEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

interface SyncPlayerListener {
    fun onInventoryLoad(event: BarcodeRepositoryDataLoadEvent)

    fun onPlayerRepositoryPreSave(event: BarcodeRepositoryPreSaveEvent)

    fun onPlayerJoin(event: PlayerJoinEvent) {}

    fun onPlayerQuit(event: PlayerQuitEvent) {}
}