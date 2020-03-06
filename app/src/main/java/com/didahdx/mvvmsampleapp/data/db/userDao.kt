package com.didahdx.mvvmsampleapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.didahdx.mvvmsampleapp.data.db.entities.CURRENT_USER_ID
import com.didahdx.mvvmsampleapp.data.db.entities.User

@Dao
interface userDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateInsert(user:User):Long

    @Query("SELECT * FROM user WHERE uid=$CURRENT_USER_ID")
    fun getUser():LiveData<User>
}