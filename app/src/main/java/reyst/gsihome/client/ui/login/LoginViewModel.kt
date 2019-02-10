package reyst.gsihome.client.ui.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import reyst.gsihome.client.R
import reyst.gsihome.client.domain.Repository
import reyst.gsihome.client.exceptions.InvalidCredentials
import reyst.gsihome.client.ui.BaseViewModel

class LoginViewModel(private val repository: Repository) : BaseViewModel() {

    val login: ObservableField<String> = ObservableField("")
    val password: ObservableField<String> = ObservableField("")

    private val loginResult = MutableLiveData<LoginResult>()

    fun getLoginResultData(): LiveData<LoginResult> = loginResult

    fun login() {

        val lLogin = login.get()
        val lPassword = password.get()

        if (lLogin != null && lPassword != null) {
            mainScope.launch {
                try {
                    val user = withContext(Dispatchers.IO) { repository.login(lLogin, lPassword) }
                    loginResult.postValue(SuccessLogin(user))
                } catch (e: Throwable) {
                    loginResult.postValue(
                        FailLogin(
                            when (e) {
                                is InvalidCredentials -> R.string.err_invalid_credentials
                                else -> R.string.err_internet
                            }
                        )
                    )
                }
            }
        }
    }
}
