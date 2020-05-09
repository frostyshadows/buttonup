package com.sherryyuan.buttonup.archives

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

/**
 * Represents a newsletter that only exists locally
 */
@Entity
data class LocalNewsletter(
    @SerializedName("username") val username: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String
)