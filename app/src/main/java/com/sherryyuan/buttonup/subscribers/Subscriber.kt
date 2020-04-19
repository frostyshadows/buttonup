package com.sherryyuan.buttonup.subscribers

import com.google.gson.annotations.SerializedName

data class Subscriber(
    @SerializedName("id") val id: String,
    @SerializedName("creation_date") val creationDate: String,
    @SerializedName("email") val email: String,
    @SerializedName("notes") val notes: String
)