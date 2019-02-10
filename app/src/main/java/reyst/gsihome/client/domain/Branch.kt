package reyst.gsihome.client.domain

import reyst.gsihome.client.ui.Displayable

data class Branch(val name: String) : Displayable {
    override fun getTextInfo(): String = name
}