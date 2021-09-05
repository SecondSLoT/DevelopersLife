package com.secondslot.seloustev

import android.app.Application
import com.secondslot.seloustev.data.db.di.DbModule
import com.secondslot.seloustev.di.AppComponent
import com.secondslot.seloustev.di.DaggerAppComponent

class DevelopersLifeApplication : Application() {

    companion object {
        private lateinit var component: AppComponent

        fun getComponent(): AppComponent {
            return component
        }
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent
            .builder()
            .dbModule(DbModule(this))
            .build()
    }
}