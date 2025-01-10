package com.salihakbas.amphibians

import android.app.Application

class Application : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }

}