package ru.skillbranch.devintensive

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import ru.skillbranch.devintensive.repositories.PreferencesReporitory

class App: Application() {

    companion object{
        private var instance: App? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    init{
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        PreferencesReporitory.getAppTheme().also {
            AppCompatDelegate.setDefaultNightMode(it)
        }
    }
}