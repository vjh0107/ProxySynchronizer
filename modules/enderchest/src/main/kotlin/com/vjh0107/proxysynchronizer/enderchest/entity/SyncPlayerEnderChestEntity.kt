package com.vjh0107.proxysynchronizer.enderchest.entity

import com.vjh0107.barcode.framework.database.exposed.entity.BarcodeEntityClass
import com.vjh0107.barcode.framework.database.exposed.entity.BarcodePlayerEntity
import com.vjh0107.barcode.framework.database.exposed.extensions.json
import com.vjh0107.barcode.framework.database.exposed.table.BarcodeIDTable
import org.jetbrains.exposed.dao.id.EntityID

class SyncPlayerEnderChestEntity(id: EntityID<Int>) :
    BarcodePlayerEntity<SyncPlayerEnderChestTable>(id, SyncPlayerEnderChestTable) {
    companion object : BarcodeEntityClass<SyncPlayerEnderChestEntity>(SyncPlayerEnderChestTable)

    var contents by SyncPlayerEnderChestTable.contents
}

object SyncPlayerEnderChestTable : BarcodeIDTable("proxysynchronizer_enderchest") {
    val contents = json("contents")
}