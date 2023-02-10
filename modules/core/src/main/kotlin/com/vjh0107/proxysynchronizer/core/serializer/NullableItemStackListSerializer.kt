package com.vjh0107.proxysynchronizer.core.serializer

import com.vjh0107.barcode.framework.serialization.serializers.ItemStackSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.nullable
import org.bukkit.inventory.ItemStack

object NullableItemStackListSerializer : KSerializer<List<ItemStack?>> by ListSerializer(ItemStackSerializer.nullable)