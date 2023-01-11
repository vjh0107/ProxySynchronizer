package com.vjh0107.proxysynchronizer.inventory.entity

import com.vjh0107.barcode.framework.database.exposed.entity.BarcodeEntityClass
import com.vjh0107.barcode.framework.database.exposed.entity.BarcodePlayerEntity
import com.vjh0107.barcode.framework.database.exposed.extensions.json
import com.vjh0107.barcode.framework.database.exposed.table.BarcodeIDTable
import com.vjh0107.barcode.framework.database.player.MinecraftPlayerID
import com.vjh0107.barcode.framework.database.player.multiprofile.ProfileID
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

class PlayerInventoryEntity(id: EntityID<Int>) : BarcodePlayerEntity(id) {
    companion object : BarcodeEntityClass<PlayerInventoryEntity>(PlayerInventoryDataTable)
    override var playerID: MinecraftPlayerID by PlayerInventoryDataTable.playerID
    override var profileID: ProfileID by PlayerInventoryDataTable.profileID

    var inventory by PlayerInventoryDataTable.inventory

    var createdAt by PlayerInventoryDataTable.createdAt
    var updatedAt by PlayerInventoryDataTable.updatedAt
}

object PlayerInventoryDataTable : BarcodeIDTable("proxysynchronizer_inventory") {
    val inventory = json("inventory")
    val createdAt = datetime("created_at").default(LocalDateTime.now()).index()
    val updatedAt = datetime("updated_at").default(LocalDateTime.now()).index()
}