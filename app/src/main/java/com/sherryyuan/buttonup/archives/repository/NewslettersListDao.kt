package com.sherryyuan.buttonup.archives.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sherryyuan.buttonup.archives.SentNewsletter
import io.reactivex.Maybe

@Dao
interface NewslettersListDao {

    @Query("SELECT * FROM sentnewsletter")
    fun getAll(): Maybe<List<SentNewsletter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(drafts: List<SentNewsletter>)
}