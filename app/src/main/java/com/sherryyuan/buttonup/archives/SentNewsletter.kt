package com.sherryyuan.buttonup.archives

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Represents a draft that has already been saved to the Buttondown server
 */
@Entity
data class SentNewsletter(
    @PrimaryKey @SerializedName("id") val id: String,
    @SerializedName("creation_date") val creationDate: String,
    @SerializedName("username") val username: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("api_key") val apiKey: String
)