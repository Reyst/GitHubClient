package reyst.gsihome.client.data.retrofit

import okhttp3.Credentials
import reyst.gsihome.client.data.DataSource
import reyst.gsihome.client.domain.Branch
import reyst.gsihome.client.domain.Commit
import reyst.gsihome.client.domain.Repo
import reyst.gsihome.client.domain.User
import reyst.gsihome.client.exceptions.InvalidCredentials

class RetrofitDataSource(
    private val service: ApiService
) : DataSource {

    private var token: String = "" // todo move to session

    private fun getTokenFor(login: String, password: String): String = Credentials.basic(login, password)

    override fun login(login: String, password: String): User {

        val requestToken = getTokenFor(login, password)
        val response = service.getUser(requestToken).execute()

        // todo make more pretty
        if (response.isSuccessful) {
            val user = response.body()?.toDomain() ?: throw InvalidCredentials()
            token = requestToken
            return user
        } else {
            throw InvalidCredentials()
        }
    }

    override fun getRepositories(user: String): List<Repo> {
        val response = service.getRepositories(user).execute()
        return if (response.isSuccessful) response.body()?.toDomain() ?: emptyList() else emptyList()
    }

    override fun getCollaborators(owner: String, repoName: String): List<User> {
        val response = service.getCollaborators(token, owner, repoName).execute()
        return if (response.isSuccessful) response.body()?.toDomain() ?: emptyList() else emptyList()
    }

    override fun getBranches(owner: String, repoName: String): List<Branch> {
        val response = service.getBranches(owner, repoName).execute()
        return if (response.isSuccessful) response.body()?.toDomain() ?: emptyList() else emptyList()
    }

    override fun getCommits(owner: String, repoName: String): List<Commit> {
        val response = service.getCommits(owner, repoName).execute()
        return if (response.isSuccessful) response.body()?.toDomain() ?: emptyList() else emptyList()
    }

}