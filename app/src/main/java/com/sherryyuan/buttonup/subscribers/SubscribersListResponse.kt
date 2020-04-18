package com.sherryyuan.buttonup.subscribers

import com.google.gson.annotations.SerializedName

data class SubscribersListResponse(
    @SerializedName("results") val results: List<Subscriber>
)