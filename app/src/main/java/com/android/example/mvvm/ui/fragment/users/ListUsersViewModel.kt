package DaggerFragment.ui.users

import DaggerFragment.util.schedulers.BaseScheduler
import com.android.example.mvvm.domain.repository.RestApiRepository
import com.android.example.mvvm.ui.base.BaseViewModel
import javax.inject.Inject
class ListUsersViewModel
@Inject constructor(
        private val scheduler: BaseScheduler,
        private val restApiRepository: RestApiRepository) : BaseViewModel() {

    fun getListUsers(){
        restApiRepository.getUsers()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .doOnSubscribe { }
                .doAfterTerminate { }
                .subscribe({ result ->
                    interfaceLoad?.onResponse(result)},
                        { throwable ->
                    interfaceLoad?.onFailure(throwable)
                })

    }
}