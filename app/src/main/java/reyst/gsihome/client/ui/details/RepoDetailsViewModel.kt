package reyst.gsihome.client.ui.details

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import reyst.gsihome.client.domain.*
import reyst.gsihome.client.ui.BaseViewModel

class RepoDetailsViewModel(private val repository: Repository) : BaseViewModel() {

    private val branches = MutableLiveData<List<Branch>>()
    private val commits = MutableLiveData<List<Commit>>()
    private val collaborators = MutableLiveData<List<User>>()

    private lateinit var repo: Repo

    fun setRepo(repo: Repo) {
        this.repo = repo
    }

    fun getBranchesData(): LiveData<List<Branch>> {
        if (branches.value.orEmpty().isEmpty()) loadBranches()
        return branches
    }

    fun getCommitsData(): LiveData<List<Commit>> {
        if (commits.value.orEmpty().isEmpty()) loadCommits()
        return commits
    }

    fun getCollaboratorsData(): LiveData<List<User>> {
        if (collaborators.value.orEmpty().isEmpty()) loadCollaborators()
        return collaborators
    }

    private fun loadCollaborators() {
        mainScope.launch {
            val users = withContext(Dispatchers.IO) { repository.getCollaborator(repo) }
            collaborators.postValue(users)
        }
    }

    private fun loadCommits() {
        mainScope.launch {
            val commitList = withContext(Dispatchers.IO) { repository.getCommits(repo) }
            commits.postValue(commitList)
        }
    }

    private fun loadBranches() {
        mainScope.launch {
            val branchesList = withContext(Dispatchers.IO) { repository.getBranches(repo) }
            branches.postValue(branchesList)
        }
    }
}