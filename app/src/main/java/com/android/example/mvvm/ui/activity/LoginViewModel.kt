package DaggerFragment.ui.users

import DaggerFragment.util.schedulers.BaseScheduler
import android.arch.lifecycle.MutableLiveData
import android.provider.ContactsContract
import android.text.TextUtils
import android.util.Patterns
import com.android.example.mvvm.domain.repository.RestApiRepository
import com.android.example.mvvm.ui.base.BaseViewModel
import javax.inject.Inject


class LoginViewModel
@Inject constructor(
        private val scheduler: BaseScheduler,
        private val restApiRepository: RestApiRepository) : BaseViewModel() {
    var emailError = MutableLiveData<Boolean>()
    var passwordError = MutableLiveData<Boolean>()
    fun validateLogin(email: String, password: String) {
        if (!validate(email, password)) {
            return
        }
        login()
    }

    fun validate(email: String, password: String): Boolean {
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            this.emailError.value = true
            return false
        }
        if (TextUtils.isEmpty(password) || password!!.length < 4 || password!!.length > 10) {
            this.passwordError.value = true
            return false
        }
        return true
    }

    fun login() {
        interfaceLoad?.onShowLoading()
        restApiRepository.getUsers()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .doOnSubscribe { }
                .doAfterTerminate { }
                .subscribe({ result ->
                    interfaceLoad?.onResponse(result)
                },
                        { throwable ->
                            interfaceLoad?.onFailure(throwable)
                        })

    }
}