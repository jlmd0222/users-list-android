package jlmd.dev.android.pruebaceiba.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String
)