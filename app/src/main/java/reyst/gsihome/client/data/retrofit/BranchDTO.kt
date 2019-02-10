package reyst.gsihome.client.data.retrofit

import reyst.gsihome.client.domain.Branch

data class BranchDTO(val name: String)

fun BranchDTO.toDomain() = Branch(name)
fun Collection<BranchDTO>.toDomain() = map { it.toDomain() }