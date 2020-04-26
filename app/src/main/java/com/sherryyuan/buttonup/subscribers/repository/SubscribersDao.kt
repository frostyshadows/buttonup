package com.sherryyuan.buttonup.subscribers.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sherryyuan.buttonup.subscribers.Subscriber
import io.reactivex.Maybe

@Dao
interface SubscribersDao {

    @Query("SELECT * FROM subscriber")
    fun getAll(): Maybe<List<Subscriber>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(subscribers: List<Subscriber>)
}