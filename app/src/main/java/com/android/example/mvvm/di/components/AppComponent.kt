package com.android.example.mvvm.di.components

import com.android.example.mvvm.App
import com.android.example.mvvm.api.di.modules.ActivityBuildersModule
import com.android.example.mvvm.di.modules.AppModule
import com.android.example.mvvm.di.modules.NetworkModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuildersModule::class,
    AppModule::class,
    NetworkModule::class
])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()

}