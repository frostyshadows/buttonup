package com.sherryyuan.buttonup.drafts

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Draft(
    @PrimaryKey @SerializedName("id") val id: String,
    @SerializedName("subject") val subject: String,
    @SerializedName("body") val body: String,
    @SerializedName("creation_date") val creationDate: String,
    @SerializedName("modification_date") val modificationDate: String
)