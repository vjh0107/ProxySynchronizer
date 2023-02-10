package com.vjh0107.proxysynchronizer.inventory.entity

import com.vjh0107.barcode.framework.database.exposed.entity.BarcodeEntityClass
import com.vjh0107.barcode.framework.database.exposed.entity.BarcodePlayerEntity
import com.vjh0107.barcode.framework.database.exposed.extensions.json
import com.vjh0107.barcode.framework.database.exposed.table.BarcodeIDTable
import org.jetbrains.exposed.dao.id.EntityID

class SyncPlayerInventoryEntity(id: EntityID<Int>) : BarcodePlayerEntity<SyncPlayerInventoryTable>(id, SyncPlayerInventoryTable) {
    companion object : BarcodeEntityClass<SyncPlayerInventoryEntity>(SyncPlayerInventoryTable)

    var storage by SyncPlayerInventoryTable.storage
    var armors by SyncPlayerInventoryTable.armors
    var extras by SyncPlayerInventoryTable.extras
}

object SyncPlayerInventoryTable : BarcodeIDTable("proxysynchronizer_inventory") {
    val storage = json("storage")
    val armors = json("armors")
    val extras = json("extras")
}