package com.android.example.mvvm.api.di.modules

import com.android.example.mvvm.ListColorsFragment
import dagger.Module
import com.android.example.mvvm.ui.fragments.users.ListUsersFragment
import dagger.android.ContributesAndroidInjector
@Module
interface FragmentBuildersModule {

    @ContributesAndroidInjector
    fun contributeListUsersFragment(): ListUsersFragment

    @ContributesAndroidInjector
    fun contributeListColorsFragment(): ListColorsFragment

}