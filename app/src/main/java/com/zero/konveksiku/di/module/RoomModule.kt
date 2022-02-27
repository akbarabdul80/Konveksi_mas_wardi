package com.zero.konveksiku.di.module

import android.content.Context
import com.zero.konveksiku.data.db.RoomDB
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {
    single { provideDatavase(androidContext()) }
}

private fun provideDatavase(
    context: Context
): RoomDB =
    RoomDB.getDatabase(context)