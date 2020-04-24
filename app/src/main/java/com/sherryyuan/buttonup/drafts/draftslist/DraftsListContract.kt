package com.sherryyuan.buttonup.drafts.draftslist

import com.sherryyuan.buttonup.drafts.Draft

interface DraftsListContract {

    interface View {
        val presenter: Presenter
        fun updateDrafts(drafts: List<Draft>)
    }

    interface Presenter {
        val view: View
        fun start()
        fun refresh()
        fun stop()
    }
}