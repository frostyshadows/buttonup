package com.sherryyuan.buttonup.archives

import com.google.gson.annotations.SerializedName

data class EmailsResponse(
    @SerializedName("results") val results: List<SentEmail>
)