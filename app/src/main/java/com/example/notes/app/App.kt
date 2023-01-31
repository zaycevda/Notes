package com.example.notes.app

import android.app.Application
import com.example.notes.di.AppComponent
import com.example.notes.di.AppModule
import com.example.notes.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}