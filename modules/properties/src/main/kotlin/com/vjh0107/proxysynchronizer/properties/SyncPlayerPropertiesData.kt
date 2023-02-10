@file:UseSerializers(PotionEffectSerializer::class)

package com.vjh0107.proxysynchronizer.properties

import com.vjh0107.barcode.framework.database.player.data.PlayerData
import com.vjh0107.proxysynchronizer.properties.util.PotionEffectSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.potion.PotionEffect

@Serializable
data class SyncPlayerPropertiesData(
    val health: Double = 20.0,
    val gameMode: GameMode = GameMode.ADVENTURE,
    val cooldowns: Map<Material, Int> = mapOf(),
    val heldItemSlot: Int = 0,
    val potions: List<PotionEffect> = listOf()
) : PlayerData