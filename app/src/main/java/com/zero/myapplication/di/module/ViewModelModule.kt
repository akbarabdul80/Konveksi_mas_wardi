package com.zero.myapplication.di.module

import com.zero.myapplication.ui.client.ClientViewModel
import com.zero.myapplication.ui.main.MainViewModel
import com.zero.myapplication.ui.rekap.RekapViewModel
import com.zero.myapplication.ui.type.TypeViewModel
import com.zero.myapplication.ui.user.UserViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { ClientViewModel(get()) }
    viewModel { UserViewModel(get()) }
    viewModel { TypeViewModel(get()) }
    viewModel { RekapViewModel(get()) }
}