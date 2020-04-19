package com.sherryyuan.buttonup.drafts

import com.google.gson.annotations.SerializedName

data class DraftsListResponse(
    @SerializedName("results") val results: List<Draft>
)