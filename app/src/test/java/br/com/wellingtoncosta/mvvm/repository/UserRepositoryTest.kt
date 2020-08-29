package br.com.wellingtoncosta.mvvm.repository

import br.com.wellingtoncosta.mvvm.data.remote.Api
import br.com.wellingtoncosta.mvvm.data.remote.response.UsersResponse
import br.com.wellingtoncosta.mvvm.domain.repository.RestApiRepository
import br.com.wellingtoncosta.mvvm.mock.Mocks.createUsers
import io.reactivex.Observable.just
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * @author wellingtoncosta on 05/02/18.
 */
@RunWith(JUnit4::class)
class UserRepositoryTest {

    @Mock
    private lateinit var api: Api

    @InjectMocks
    private lateinit var userRepository: RestApiRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        `when`(userRepository.getUsers()).thenReturn(just(UsersResponse(
                0,
                0,
                0,
                0,
                createUsers()
        )))
    }

    @Test
    fun getUsersWithSuccess() {
        userRepository.getUsers()
        verify<Api>(api).fetchUsers()
    }

}