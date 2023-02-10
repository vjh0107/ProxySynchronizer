package com.vjh0107.proxysynchronizer.enderchest

import com.vjh0107.barcode.framework.serialization.deserialize
import com.vjh0107.proxysynchronizer.core.serializer.NullableItemStackListSerializer
import com.vjh0107.proxysynchronizer.enderchest.entity.SyncPlayerEnderChestEntity
import org.bukkit.entity.Player
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named

@Module
class SyncPlayerEnderChestDataFactory {
    @Named("default")
    @Factory
    fun provideSyncPlayerEnderChestDataDefault(): SyncPlayerEnderChestData {
        return SyncPlayerEnderChestData()
    }

    @Named("player")
    @Factory
    fun provideSyncPlayerEnderChestDataByPlayer(@InjectedParam player: Player): SyncPlayerEnderChestData {
        return SyncPlayerEnderChestData(player.enderChest.contents.toList())
    }

    @Named("entity")
    @Factory
    fun provideSyncPlayerEnderChestDataByEntity(@InjectedParam entity: SyncPlayerEnderChestEntity): SyncPlayerEnderChestData {
        return SyncPlayerEnderChestData(entity.contents.deserialize(NullableItemStackListSerializer))
    }
}