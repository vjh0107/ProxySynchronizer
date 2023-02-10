package com.vjh0107.proxysynchronizer.enderchest

import com.vjh0107.barcode.framework.component.BarcodeComponent
import com.vjh0107.barcode.framework.component.BarcodeKoinModule
import com.vjh0107.barcode.framework.component.KoinModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.ksp.generated.module

@Module(includes = [SyncPlayerEnderChestDataFactory::class])
@ComponentScan
@BarcodeComponent
class SyncPlayerEnderChestModule : BarcodeKoinModule {
    override val targetModule: KoinModule
        get() = module
}