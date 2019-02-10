package reyst.gsihome.client.ui.login

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import org.koin.android.viewmodel.ext.android.viewModel
import reyst.gsihome.client.R
import reyst.gsihome.client.databinding.ActivityLoginBinding
import reyst.gsihome.client.domain.User
import reyst.gsihome.client.ui.user.UserActivity


class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.model = viewModel

        viewModel.getLoginResultData().observe(
            this,
            Observer {data ->
                when (data) {
                    is SuccessLogin -> startUserActivity(data.user)
                    is FailLogin -> Toast.makeText(this, data.stringId, Toast.LENGTH_LONG).show()
                    else -> {}
                }
            }
        )
    }

    private fun startUserActivity(user: User) {
        UserActivity.start(this, user)
        finish()
    }
}
