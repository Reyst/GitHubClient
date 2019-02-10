package reyst.gsihome.client.domain

import reyst.gsihome.client.ui.Displayable
import reyst.gsihome.client.ui.UrlBrowsable

data class Commit(
    val id: String,
    val message: String,
    val url: String
): Displayable, UrlBrowsable {

    override fun getTextInfo(): String = "$id\n$message"

    override fun getDetailsUrl(): String = url
}
