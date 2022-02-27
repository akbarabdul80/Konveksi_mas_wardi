package com.zero.konveksiku.di.module

import com.zero.konveksiku.ui.client.ClientViewModel
import com.zero.konveksiku.ui.main.MainViewModel
import com.zero.konveksiku.ui.rekap.RekapViewModel
import com.zero.konveksiku.ui.type.TypeViewModel
import com.zero.konveksiku.ui.user.UserViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { ClientViewModel(get()) }
    viewModel { UserViewModel(get()) }
    viewModel { TypeViewModel(get()) }
    viewModel { RekapViewModel(get()) }
}