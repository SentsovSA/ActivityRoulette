package model

import kotlinx.serialization.Serializable

@Serializable
data class RandomActivity(
    val activity: String?,
    val type: String?,
    val participants: Int?,
    val price: Double?,
    val link: String?,
    val key: String?,
    val accessibility: Double?,
)


