package com.sherryyuan.buttonup

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sherryyuan.buttonup.drafts.SavedDraft
import com.sherryyuan.buttonup.drafts.repository.DraftsListDao

@Database(entities = [SavedDraft::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun draftsListDao(): DraftsListDao
}