package com.sherryyuan.buttonup.drafts

interface DraftsContract {

    interface View {
        val presenter: Presenter
        fun updateDrafts(drafts: List<Draft>)
    }

    interface Presenter {
        val view: View
        fun start()
        fun stop()
        fun fetchDrafts()
    }
}