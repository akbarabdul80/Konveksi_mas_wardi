package com.zero.konveksiku.root

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.zero.konveksiku.di.module.roomModule
import com.zero.konveksiku.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this) {}

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    roomModule,
                    viewModelModule
                )
            )
        }
    }

}