package reyst.gsihome.client.data.retrofit

import com.google.gson.annotations.SerializedName
import reyst.gsihome.client.domain.User

class UserDTO(
    @SerializedName("login")
    val login: String,

    @SerializedName("avatar_url")
    val avatarUrl: String,

    @SerializedName("html_url")
    val profileUrl: String
)

fun UserDTO.toDomain() = User(login, avatarUrl, profileUrl)
fun Collection<UserDTO>.toDomain() = map { it.toDomain() }
