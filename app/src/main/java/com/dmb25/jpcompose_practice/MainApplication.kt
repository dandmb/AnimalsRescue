package com.dmb25.jpcompose_practice

import android.app.Application
import com.dmb25.jpcompose_practice.di.dataModule
import com.dmb25.jpcompose_practice.di.domainModule
import com.dmb25.jpcompose_practice.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(dataModule, domainModule, presentationModule)
        }
    }
}