package br.com.wellingtoncosta.mvvm.repository

import br.com.wellingtoncosta.mvvm.data.remote.Api
import br.com.wellingtoncosta.mvvm.data.remote.response.UsersResponse
import br.com.wellingtoncosta.mvvm.mock.Mocks.createColors
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
class ColorRepositoryTesst {

    @Mock
    private lateinit var api: Api

    @InjectMocks
    private lateinit var colorRepository: ColorRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        `when`(colorRepository.getColors()).thenReturn(just(UsersResponse(
                0,
                0,
                0,
                0,
                createColors()
        )))
    }

    @Test
    fun getColorsWithSuccess() {
        colorRepository.getColors()
        verify<Api>(api).fetchColors()
    }

}