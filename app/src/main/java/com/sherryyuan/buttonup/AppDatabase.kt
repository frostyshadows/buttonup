package com.sherryyuan.buttonup

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sherryyuan.buttonup.archives.SentEmail
import com.sherryyuan.buttonup.archives.repository.EmailsListDao
import com.sherryyuan.buttonup.drafts.SavedDraft
import com.sherryyuan.buttonup.drafts.repository.DraftsListDao
import com.sherryyuan.buttonup.subscribers.Subscriber
import com.sherryyuan.buttonup.subscribers.repository.SubscribersDao

@Database(entities = [SavedDraft::class, Subscriber::class, SentEmail::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun draftsListDao(): DraftsListDao

    abstract fun subscribersDao(): SubscribersDao

    abstract fun emailsListDao(): EmailsListDao
}