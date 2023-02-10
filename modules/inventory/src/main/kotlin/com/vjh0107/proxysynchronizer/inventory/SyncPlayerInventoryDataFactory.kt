package com.vjh0107.proxysynchronizer.inventory

import com.vjh0107.barcode.framework.serialization.deserialize
import com.vjh0107.proxysynchronizer.inventory.entity.SyncPlayerInventoryEntity
import com.vjh0107.proxysynchronizer.core.serializer.NullableItemStackListSerializer
import org.bukkit.entity.Player
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named

@Module
class SyncPlayerInventoryDataFactory {
    @Named("player")
    @Factory
    fun provideSyncPlayerInventoryDataByPlayer(@InjectedParam player: Player): SyncPlayerInventoryData {
        return player.inventory.run {
            SyncPlayerInventoryData(
                storageContents.toList(),
                armorContents.toList(),
                extraContents.toList()
            )
        }
    }

    @Named("default")
    @Factory
    fun provideSyncPlayerInventoryDataDefault(): SyncPlayerInventoryData {
        return SyncPlayerInventoryData()
    }

    @Named("entity")
    @Factory
    fun provideSyncPlayerInventoryDataByEntity(@InjectedParam entity: SyncPlayerInventoryEntity): SyncPlayerInventoryData {
        val storage = entity.storage.deserialize(NullableItemStackListSerializer)
        val armors = entity.armors.deserialize(NullableItemStackListSerializer)
        val extras = entity.extras.deserialize(NullableItemStackListSerializer)
        return SyncPlayerInventoryData(storage, armors, extras)
    }
}