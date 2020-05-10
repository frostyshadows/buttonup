package com.sherryyuan.buttonup.archives

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

/**
 * Represents a newsletter that only exists locally
 */
@Entity
data class LocalEmail(
    @SerializedName("publish_date") val creationDate: String,
    @SerializedName("subject") val subject: String,
    @SerializedName("body") val body: String
)