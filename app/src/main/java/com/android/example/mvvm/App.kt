package com.android.example.mvvm

import android.content.Context
import com.android.example.mvvm.di.components.DaggerAppComponent
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
open class App : DaggerApplication() {
    companion object{
        var sApplication: App? = null
    }

    override fun onCreate() {
        sApplication = this
        super.onCreate()
        Fresco.initialize(this)
    }

    override fun applicationInjector(): AndroidInjector<out App> {
        return DaggerAppComponent.builder().create(this)
    }
    fun getApplication(): App {
        return sApplication as App
    }

    fun getContext(): Context? {
        return getApplication().getApplicationContext()
    }

}