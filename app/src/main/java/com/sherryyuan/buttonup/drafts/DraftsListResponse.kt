package com.sherryyuan.buttonup.drafts

import com.google.gson.annotations.SerializedName
import com.sherryyuan.buttonup.drafts.SavedDraft

data class DraftsListResponse(
    @SerializedName("results") val results: List<SavedDraft>
)