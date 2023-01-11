package com.vjh0107.proxysynchronizer.inventory

import com.vjh0107.barcode.framework.component.BarcodeKoinModule
import com.vjh0107.barcode.framework.component.KoinModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.ksp.generated.module

@Module(includes = [SyncPlayerInventoryDataFactory::class])
@ComponentScan
class SyncPlayerInventoryModule : BarcodeKoinModule {
    override val targetModule: KoinModule
        get() = module
}