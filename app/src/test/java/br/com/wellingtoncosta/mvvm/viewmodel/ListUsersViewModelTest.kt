package br.com.wellingtoncosta.mvvm.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import br.com.wellingtoncosta.mvvm.data.remote.response.UsersResponse
import br.com.wellingtoncosta.mvvm.domain.model.User
import br.com.wellingtoncosta.mvvm.domain.repository.RestApiRepository
import br.com.wellingtoncosta.mvvm.mock.Mocks.createUsers
import br.com.wellingtoncosta.mvvm.ui.users.ListUsersViewModel
import io.reactivex.Observable.just
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

/**
 * @author wellingtoncosta on 05/02/18.
 */
@RunWith(JUnit4::class)
class ListUsersViewModelTest {

    @Rule
    @JvmField
    val instantExecutor = InstantTaskExecutorRule()

    private lateinit var userRepository: RestApiRepository

    private lateinit var listUsersViewModel: ListUsersViewModel

    @Before
    fun setUp() {
        userRepository = mock<RestApiRepository>(RestApiRepository::class.java)
        listUsersViewModel = ListUsersViewModel(
                TestSchedulerProvider(TestScheduler()),
                userRepository
        )
        `when`(userRepository.getUsers()).thenReturn(just(UsersResponse(
                0,
                0,
                0,
                0,
                createUsers()
        )))
    }

    @Test
    fun empty() {
        val result = mock(Observer::class.java)
        listUsersViewModel.response.observeForever(result as Observer<Response<List<User>>>)
        verifyNoMoreInteractions(userRepository)
    }

    @Test
    fun loadUsersWithSuccess() {
        val result = mock(Observer::class.java)
        listUsersViewModel.response.observeForever(result as Observer<Response<List<User>>>)
        listUsersViewModel.getListUsers()
        verify<RestApiRepository>(userRepository).getUsers()
    }

}