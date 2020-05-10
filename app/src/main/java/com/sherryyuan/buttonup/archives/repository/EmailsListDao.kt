package com.sherryyuan.buttonup.archives.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sherryyuan.buttonup.archives.SentEmail
import io.reactivex.Maybe

@Dao
interface EmailsListDao {

    @Query("SELECT * FROM SentEmail")
    fun getAll(): Maybe<List<SentEmail>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(emails: List<SentEmail>)
}