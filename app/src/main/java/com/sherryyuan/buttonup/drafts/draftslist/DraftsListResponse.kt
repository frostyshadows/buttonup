package com.sherryyuan.buttonup.drafts.draftslist

import com.google.gson.annotations.SerializedName
import com.sherryyuan.buttonup.drafts.Draft

data class DraftsListResponse(
    @SerializedName("results") val results: List<Draft>
)