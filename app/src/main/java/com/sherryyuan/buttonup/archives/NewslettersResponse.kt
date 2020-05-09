package com.sherryyuan.buttonup.archives

import com.google.gson.annotations.SerializedName

data class NewslettersResponse(
    @SerializedName("results") val results: List<SentNewsletter>
)