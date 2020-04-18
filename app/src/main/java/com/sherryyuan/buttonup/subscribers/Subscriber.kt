package com.sherryyuan.buttonup.subscribers

import com.google.gson.annotations.SerializedName

data class Subscriber(
    @SerializedName("creation_date") val creationDate: String,
    @SerializedName("email") val email: String,
    @SerializedName("id") val id: String,
    @SerializedName("notes") val notes: String
)