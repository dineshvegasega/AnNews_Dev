package com.vegasega.amnews.datastore.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MusicDao {


    @Query("SELECT * FROM musicmodel")
    suspend fun getAll(): List<MusicModel>

    @Query("SELECT * FROM musicmodel WHERE uid IN (:userIds)")
    suspend fun loadAllByIds(userIds: IntArray): List<MusicModel>

    @Query("SELECT * FROM musicmodel WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    suspend fun findByName(first: String, last: String): MusicModel

    @Insert
    suspend fun insertAll(vararg users: MusicModel)

    @Delete
    suspend fun delete(user: MusicModel)


}