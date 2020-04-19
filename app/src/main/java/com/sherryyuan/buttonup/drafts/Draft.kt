package com.sherryyuan.buttonup.drafts

import com.google.gson.annotations.SerializedName

data class Draft(
    @SerializedName("id") val id: String,
    @SerializedName("subject") val subject: String,
    @SerializedName("body") val body: String,
    @SerializedName("creation_date") val creationDate: String,
    @SerializedName("modification_date") val modificationDate: String
)