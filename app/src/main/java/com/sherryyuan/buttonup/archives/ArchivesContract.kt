package com.sherryyuan.buttonup.archives

interface ArchivesContract {

    interface View {
        val presenter: Presenter
        fun updateEmails(emails: List<SentEmail>)
    }

    interface Presenter {
        val view: View
        fun start()
        fun refresh()
        fun stop()
    }
}