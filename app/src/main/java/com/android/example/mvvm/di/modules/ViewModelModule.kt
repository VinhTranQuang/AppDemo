package com.android.example.mvvm.di.modules

import DaggerFragment.ui.users.ListUsersViewModel
import DaggerFragment.ui.users.LoginViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import br.com.wellingtoncosta.mvvm.ui.colors.ListColorsViewModel
import com.android.example.mvvm.di.keys.ViewModelKey
import com.android.example.mvvm.ui.common.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListUsersViewModel::class)
    fun bindListUsersViewModel(listUsersViewModel: ListUsersViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListColorsViewModel::class)
    fun bindListColorsViewModel(listColorsViewModel: ListColorsViewModel) : ViewModel

    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindLoginViewModel(loginViewModel: LoginViewModel) : ViewModel

}