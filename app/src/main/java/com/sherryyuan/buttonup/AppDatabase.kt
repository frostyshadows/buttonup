package com.sherryyuan.buttonup

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sherryyuan.buttonup.drafts.Draft
import com.sherryyuan.buttonup.drafts.repository.DraftsDao

@Database(entities = [Draft::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun draftsDao(): DraftsDao
}