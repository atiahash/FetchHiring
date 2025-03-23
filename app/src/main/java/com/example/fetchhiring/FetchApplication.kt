package com.example.fetchhiring

import android.app.Application

class FetchApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        HireContainer(applicationContext)
    }
}