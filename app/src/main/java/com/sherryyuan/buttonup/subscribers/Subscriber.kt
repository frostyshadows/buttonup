package com.sherryyuan.buttonup.subscribers

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Subscriber(
    @PrimaryKey @SerializedName("id") val id: String,
    @SerializedName("creation_date") val creationDate: String,
    @SerializedName("email") val email: String,
    @SerializedName("notes") val notes: String
)