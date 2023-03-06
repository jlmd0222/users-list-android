package jlmd.dev.android.pruebaceiba.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jlmd.dev.android.pruebaceiba.data.database.entities.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table ORDER BY id ASC")
    suspend fun getUsersLocal(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUsers(posts: List<UserEntity>)

    @Query("SELECT * FROM user_table WHERE id = :userId")
    suspend fun getUserById(userId: Int): UserEntity
}