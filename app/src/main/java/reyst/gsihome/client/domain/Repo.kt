package reyst.gsihome.client.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import reyst.gsihome.client.ui.Displayable

@Parcelize
data class Repo(
    val id: Int,
    val name: String,
    val fullName: String,
    val owner: User
) : Displayable, Parcelable {
    override fun getTextInfo() = name
}