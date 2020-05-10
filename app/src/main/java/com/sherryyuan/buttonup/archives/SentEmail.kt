package com.sherryyuan.buttonup.archives

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Represents a draft that has already been saved to the Buttondown server
 */
@Entity
data class SentEmail(
    @PrimaryKey @SerializedName("id") val id: String,
    @SerializedName("publish_date") val creationDate: String,
    @SerializedName("subject") val subject: String,
    @SerializedName("body") val body: String
)