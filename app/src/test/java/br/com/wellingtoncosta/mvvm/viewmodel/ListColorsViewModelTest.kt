package br.com.wellingtoncosta.mvvm.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import br.com.wellingtoncosta.mvvm.data.remote.response.UsersResponse
import br.com.wellingtoncosta.mvvm.domain.model.Color
import br.com.wellingtoncosta.mvvm.mock.Mocks.createColors
import br.com.wellingtoncosta.mvvm.ui.colors.ListColorsViewModel
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
class ListColorsViewModelTest {

    @Rule
    @JvmField
    val instantExecutor = InstantTaskExecutorRule()

    private lateinit var colorRepository: ColorRepository

    private lateinit var listColorsViewModel: ListColorsViewModel

    @Before
    fun setUp() {
        colorRepository = mock<ColorRepository>(ColorRepository::class.java)
        listColorsViewModel = ListColorsViewModel(
                TestSchedulerProvider(TestScheduler()),
                colorRepository
        )
        `when`(colorRepository.getColors()).thenReturn(just(UsersResponse(
                0,
                0,
                0,
                0,
                createColors()
        )))
    }

    @Test
    fun empty() {
        val result = mock(Observer::class.java)
        listColorsViewModel.response.observeForever(result as Observer<Response<List<Color>>>)
        verifyNoMoreInteractions(colorRepository)
    }

    @Test
    fun loadColorsWithSuccess() {
        val result = mock(Observer::class.java)
        listColorsViewModel.response.observeForever(result as Observer<Response<List<Color>>>)
        listColorsViewModel.getListColors()
        verify<ColorRepository>(colorRepository).getColors()
    }

}