package reyst.gsihome.client.ui.user

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel
import reyst.gsihome.client.R
import reyst.gsihome.client.databinding.ActivityUserBinding
import reyst.gsihome.client.domain.Repo
import reyst.gsihome.client.domain.User
import reyst.gsihome.client.ui.DisplayableAdapter
import reyst.gsihome.client.ui.details.RepoDetailsActivity

class UserActivity : AppCompatActivity() {

    private val viewModel: UserViewModel by viewModel()

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user: User = intent.getParcelableExtra(KEY_USER)

        viewModel.setUser(user) // todo move to session

        binding = DataBindingUtil.setContentView(this, R.layout.activity_user)
        binding.user = user

        binding.rvRepos.layoutManager = LinearLayoutManager(this)
        viewModel.getUserRepositoriesData().observe(
            this,
            Observer { data ->
                data?.also {
                    binding.rvRepos.adapter = DisplayableAdapter(data) { openDetails(it as Repo) }
                }
            }
        )
    }

    private fun openDetails(repo: Repo) {
        RepoDetailsActivity.start(this, repo)
    }

    companion object {
        private const val KEY_USER = "user"

        fun start(activity: AppCompatActivity, user: User) {
            val intent = Intent(activity, UserActivity::class.java)
                .apply {
                    putExtra(KEY_USER, user)
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                }

            activity.startActivity(intent)
        }
    }
}
