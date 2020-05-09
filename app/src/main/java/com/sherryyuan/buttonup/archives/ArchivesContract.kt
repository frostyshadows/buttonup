package com.sherryyuan.buttonup.archives

interface ArchivesContract {

    interface View {
        val presenter: Presenter
        fun updateNewsletters(newsletters: List<SentNewsletter>)
    }

    interface Presenter {
        val view: View
        fun start()
        fun refresh()
        fun stop()
    }
}