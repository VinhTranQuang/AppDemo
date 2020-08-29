package br.com.wellingtoncosta.mvvm.di

import br.com.wellingtoncosta.mvvm.data.remote.Api
import br.com.wellingtoncosta.mvvm.data.remote.response.UsersResponse
import br.com.wellingtoncosta.mvvm.di.modules.ViewModelModule
import br.com.wellingtoncosta.mvvm.domain.repository.RestApiRepository
import br.com.wellingtoncosta.mvvm.mock.Mocks.createColors
import br.com.wellingtoncosta.mvvm.mock.Mocks.createUsers
import dagger.Module
import dagger.Provides
import io.reactivex.Observable.just
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import javax.inject.Singleton

/**
 * @author wellingtoncosta on 06/02/18.
 */
@Module(includes = [(ViewModelModule::class)])
internal class TestAppModule {

    @Provides
    @Singleton
    fun provideApi(): Api {
        val api = mock<Api>(Api::class.java)
        `when`(api.fetchUsers()).thenReturn(just(UsersResponse(
                0,
                0,
                0,
                0,
                createUsers()
        )))
        `when`(api.fetchColors()).thenReturn(just(UsersResponse(
                0,
                0,
                0,
                0,
                createColors()
        )))
        return api
    }

    @Provides
    @Singleton
    fun provideUserRepository(api: Api): RestApiRepository {
        return RestApiRepository(api)
    }

    @Provides
    @Singleton
    fun provideRepoRepository(api: Api): ColorRepository {
        return ColorRepository(api)
    }

    @Provides
    @Singleton
    fun provideScheduler(): BaseScheduler {
        return SchedulerProvider()
    }

}