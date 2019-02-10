package reyst.gsihome.client.data.retrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    @GET("user")
    fun getUser(@Header("Authorization") token: String): Call<UserDTO>

    @GET("users/{user}/repos")
    fun getRepositories(@Path("user") user: String): Call<List<RepositoryDTO>>

    @GET("/repos/{owner}/{repo}/branches")
    fun getBranches(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Call<List<BranchDTO>>

    @GET("/repos/{owner}/{repo}/collaborators")
    fun getCollaborators(
        @Header("Authorization") token: String,
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Call<List<UserDTO>>

    @GET("/repos/{owner}/{repo}/commits")
    fun getCommits(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Call<List<CommitDTO>>



}