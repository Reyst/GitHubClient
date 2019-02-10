package reyst.gsihome.client.ui.user

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import reyst.gsihome.client.domain.Repo
import reyst.gsihome.client.domain.Repository
import reyst.gsihome.client.domain.User
import reyst.gsihome.client.ui.BaseViewModel

class UserViewModel(private val repository: Repository) : BaseViewModel() {

    private val userRepositories = MutableLiveData<List<Repo>>()

    private lateinit var user: User

    fun setUser(user: User) {
        this.user = user
    }

    fun getUserRepositoriesData(): LiveData<List<Repo>> {
        if (userRepositories.value.orEmpty().isEmpty()) loadUserRepositories()
        return userRepositories
    }

    private fun loadUserRepositories() {
        mainScope.launch {
            val repositoryList = withContext(Dispatchers.IO) { repository.getRepositories(user) }
            userRepositories.postValue(repositoryList)
        }
    }
}