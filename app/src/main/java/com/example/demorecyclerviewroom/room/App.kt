package com.example.demorecyclerviewroom.room

import android.app.Application

class App : Application() {

    private val appDataBase by lazy { AppDataBase.getDatabase(this) }
    val appRepo by lazy { AppRepo(appDataBase.appDao()) }
}