package reyst.gsihome.client.ui.details

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.koin.android.viewmodel.ext.android.viewModel
import reyst.gsihome.client.R
import reyst.gsihome.client.databinding.ActivityRepoDetailsBinding
import reyst.gsihome.client.domain.Repo
import reyst.gsihome.client.ui.Displayable
import reyst.gsihome.client.ui.DisplayableAdapter
import reyst.gsihome.client.ui.UrlBrowsable


class RepoDetailsActivity : AppCompatActivity() {

    private val viewModel: RepoDetailsViewModel by viewModel()

    private lateinit var binding: ActivityRepoDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repo = intent.getParcelableExtra<Repo>(KEY_REPO)
        viewModel.setRepo(repo)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_repo_details)
        binding.repo = repo

        binding.rvBranches.layoutManager = LinearLayoutManager(this)
        binding.rvCollaborators.layoutManager = LinearLayoutManager(this)
        binding.rvCommits.layoutManager = LinearLayoutManager(this)

        viewModel.getBranchesData().observe(this, Observer { actionOn(binding.rvBranches, it) })
        viewModel.getCommitsData().observe(this, Observer { actionOn(binding.rvCommits, it) })
        viewModel.getCollaboratorsData().observe(this, Observer { actionOn(binding.rvCollaborators, it) })
    }

    private fun actionOn(recyclerView: RecyclerView, data: List<Displayable>?) {
        if (data != null) {
            recyclerView.adapter = DisplayableAdapter(data) {
                if (it is UrlBrowsable) openInBrowser(it.getDetailsUrl())
            }
        }
    }

    private fun openInBrowser(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(url) })
    }

    companion object {
        private const val KEY_REPO = "repo"

        fun start(activity: AppCompatActivity, repo: Repo) {
            val intent = Intent(activity, RepoDetailsActivity::class.java)
                .apply { putExtra(KEY_REPO, repo) }

            activity.startActivity(intent)
        }
    }
}
