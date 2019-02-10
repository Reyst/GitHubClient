package reyst.gsihome.client.data.retrofit

import com.google.gson.annotations.SerializedName
import reyst.gsihome.client.domain.Commit

data class CommitDTO (
    val sha: String,

    @SerializedName("commit")
    val commitMessage: CommitMessage,

    @SerializedName("html_url")
    val url: String
)

data class CommitMessage(
    val message: String
)

fun CommitDTO.toDomain() = Commit(sha, commitMessage.message, url)
fun Collection<CommitDTO>.toDomain() = map { it.toDomain() }
