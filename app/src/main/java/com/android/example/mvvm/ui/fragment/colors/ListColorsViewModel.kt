package br.com.wellingtoncosta.mvvm.ui.colors

import DaggerFragment.util.schedulers.BaseScheduler
import com.android.example.mvvm.domain.repository.RestApiRepository
import com.android.example.mvvm.ui.base.BaseViewModel
import javax.inject.Inject
class ListColorsViewModel
@Inject constructor(
        private val scheduler: BaseScheduler,
        private val restApiRepository: RestApiRepository) : BaseViewModel() {

    fun getListColors(){
        restApiRepository.getColors()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .doOnSubscribe {  }
                .doAfterTerminate {  }
                .subscribe({
                    result -> interfaceLoad?.onResponse(result)
                }, {
                    throwable -> interfaceLoad?.onFailure(throwable)
                })
    }

}