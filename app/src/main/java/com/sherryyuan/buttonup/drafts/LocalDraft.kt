package com.sherryyuan.buttonup.drafts

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

/**
 * Represents a draft that only exists locally
 */
@Entity
data class LocalDraft(
    @SerializedName("subject") val subject: String,
    @SerializedName("body") val body: String
)