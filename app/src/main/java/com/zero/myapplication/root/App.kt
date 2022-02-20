package com.zero.myapplication.root

import android.app.Application
import com.zero.myapplication.di.module.roomModule
import com.zero.myapplication.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
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