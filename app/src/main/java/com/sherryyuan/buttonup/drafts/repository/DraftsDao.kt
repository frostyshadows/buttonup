package com.sherryyuan.buttonup.drafts.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sherryyuan.buttonup.drafts.Draft
import io.reactivex.Maybe

@Dao
interface DraftsDao {

    @Query("SELECT * FROM draft")
    fun getAll(): Maybe<List<Draft>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(drafts: List<Draft>)
}