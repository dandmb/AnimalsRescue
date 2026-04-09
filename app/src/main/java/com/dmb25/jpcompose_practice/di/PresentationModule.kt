package com.dmb25.jpcompose_practice.di

import com.dmb25.jpcompose_practice.presentation.ui.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { HomeViewModel(get(), get(), get(), get()) }
}