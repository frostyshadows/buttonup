package com.sherryyuan.buttonup.drafts.writedraft

import com.sherryyuan.buttonup.drafts.LocalDraft

interface WriteDraftContract {

    interface View {
        val presenter: Presenter
        fun onDraftSaved()
    }

    interface Presenter {
        val view: View
        fun start()
        fun stop()
        fun saveDraft(draft: LocalDraft)
    }
}