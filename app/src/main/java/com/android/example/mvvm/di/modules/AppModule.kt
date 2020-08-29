package com.android.example.mvvm.di.modules

import DaggerFragment.util.schedulers.BaseScheduler
import DaggerFragment.util.schedulers.SchedulerProvider
import com.android.example.mvvm.api.Api
import com.android.example.mvvm.domain.repository.RestApiRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton
@Module(includes = [(ViewModelModule::class)])
class AppModule {

    @Provides
    @Singleton
    fun provideApi(retorfit: Retrofit) : Api {
        return retorfit.create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(api: Api): RestApiRepository {
        return RestApiRepository(api)
    }

    @Provides
    @Singleton
    fun provideScheduler(): BaseScheduler {
        return SchedulerProvider()
    }

}