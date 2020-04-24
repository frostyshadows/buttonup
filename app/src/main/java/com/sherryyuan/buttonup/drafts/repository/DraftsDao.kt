package com.sherryyuan.buttonup.drafts.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sherryyuan.buttonup.drafts.SavedDraft
import io.reactivex.Maybe

@Dao
interface DraftsDao {

    @Query("SELECT * FROM saveddraft")
    fun getAll(): Maybe<List<SavedDraft>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(drafts: List<SavedDraft>)
}