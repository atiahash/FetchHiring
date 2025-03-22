package com.example.fetchhiring

import android.app.Application

class FetchApplication: Application() {
    lateinit var appContainer: HireContainer
    override fun onCreate() {
        super.onCreate()
        appContainer = HireContainer()
    }
}