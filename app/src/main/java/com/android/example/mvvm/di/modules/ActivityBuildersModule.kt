package com.android.example.mvvm.api.di.modules

import com.android.example.mvvm.ui.LoginActivity
import com.android.example.mvvm.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
@Module
interface ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    fun contributeMainActivity(): MainActivity
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    fun contributeLoginActivity(): LoginActivity

}