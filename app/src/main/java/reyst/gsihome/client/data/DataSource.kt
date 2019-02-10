package reyst.gsihome.client.data

import reyst.gsihome.client.domain.Branch
import reyst.gsihome.client.domain.Commit
import reyst.gsihome.client.domain.Repo
import reyst.gsihome.client.domain.User
import reyst.gsihome.client.exceptions.InvalidCredentials

interface DataSource {

    @Throws(InvalidCredentials::class)
    fun login(login: String, password: String): User

    fun getRepositories(user: String): List<Repo>
    fun getCollaborators(owner: String, repoName: String): List<User>
    fun getBranches(owner: String, repoName: String): List<Branch>
    fun getCommits(owner: String, repoName: String): List<Commit>
}