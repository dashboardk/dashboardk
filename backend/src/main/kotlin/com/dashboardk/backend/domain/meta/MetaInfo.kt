package com.dashboardk.backend.domain.meta

import com.dashboardk.backend.domain.widgets.Widget
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetaInfo(
    @SerialName("repos") val repos: List<RepoMetaInfo>?,
    @SerialName("widgets") val widgets: List<Widget>
)

@Serializable
data class RepoMetaInfo(
    @SerialName("name") val name: String,
    @SerialName("provider") val provider: RepoProvider,
    @SerialName("token") val token: String
)

@Serializable
enum class RepoProvider {
    @SerialName("github")
    GITHUB
}