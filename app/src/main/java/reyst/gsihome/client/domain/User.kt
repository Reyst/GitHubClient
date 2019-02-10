package reyst.gsihome.client.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import reyst.gsihome.client.ui.Displayable
import reyst.gsihome.client.ui.UrlBrowsable

@Parcelize
data class User(
    val login: String,
    val avatarUrl: String,
    val profileUrl: String
): Parcelable, Displayable, UrlBrowsable {

    override fun getTextInfo(): String = login

    override fun getDetailsUrl(): String = profileUrl
}