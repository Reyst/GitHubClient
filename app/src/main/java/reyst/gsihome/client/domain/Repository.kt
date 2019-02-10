package reyst.gsihome.client.domain

import android.support.annotation.WorkerThread
import reyst.gsihome.client.exceptions.InvalidCredentials

interface Repository {

    @WorkerThread
    @Throws(InvalidCredentials::class)
    fun login(login: String, password: String): User

    @WorkerThread
    fun getRepositories(user: User): List<Repo>

    @WorkerThread
    fun getBranches(repo: Repo): List<Branch>

    @WorkerThread
    fun getCommits(repo: Repo): List<Commit>

    @WorkerThread
    fun getCollaborator(repo: Repo): List<User>

}