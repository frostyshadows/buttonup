package com.sherryyuan.buttonup.subscribers

interface SubscribersContract {

    interface View {
        val presenter: Presenter
        fun updateSubscribersList(subscribers: List<Subscriber>)
    }

    interface Presenter {
        val view: View
        fun start()
        fun refresh()
        fun stop()
    }
}