package com.sherryyuan.buttonup.drafts.writedraft

import com.sherryyuan.buttonup.drafts.Draft

interface WriteDraftContract {

    interface View {
        val presenter: Presenter
    }

    interface Presenter {
        fun saveDraft(draft: Draft)
    }
}