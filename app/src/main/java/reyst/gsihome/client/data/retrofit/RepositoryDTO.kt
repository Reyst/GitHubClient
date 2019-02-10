package reyst.gsihome.client.data.retrofit

import com.google.gson.annotations.SerializedName
import reyst.gsihome.client.domain.Repo

data class RepositoryDTO(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("full_name")
    val fullName: String,

    @SerializedName("owner")
    val owner: UserDTO
)

fun RepositoryDTO.toDomain() = Repo(id, name, fullName, owner.toDomain())
fun Collection<RepositoryDTO>.toDomain() = map { it.toDomain() }