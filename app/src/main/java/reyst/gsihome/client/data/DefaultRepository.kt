package reyst.gsihome.client.data

import reyst.gsihome.client.domain.*

class DefaultRepository(
    private val dataSource: DataSource
) : Repository {

    override fun login(login: String, password: String): User = dataSource.login(login, password)

    override fun getRepositories(user: User): List<Repo> = dataSource.getRepositories(user.login)

    override fun getBranches(repo: Repo): List<Branch> = dataSource.getBranches(repo.owner.login, repo.name)

    override fun getCommits(repo: Repo): List<Commit> = dataSource.getCommits(repo.owner.login, repo.name)

    override fun getCollaborator(repo: Repo): List<User> = dataSource.getCollaborators(repo.owner.login, repo.name)
}