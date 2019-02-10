package reyst.gsihome.client.ui.login

import android.support.annotation.StringRes
import reyst.gsihome.client.domain.User

sealed class LoginResult

data class SuccessLogin(val user: User) : LoginResult()

data class FailLogin(@StringRes val stringId: Int) : LoginResult()