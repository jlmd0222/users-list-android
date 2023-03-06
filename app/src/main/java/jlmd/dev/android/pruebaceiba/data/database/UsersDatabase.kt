package jlmd.dev.android.pruebaceiba.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import jlmd.dev.android.pruebaceiba.data.database.daos.UserDao
import jlmd.dev.android.pruebaceiba.data.database.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = true)
abstract class UsersDatabase: RoomDatabase() {

    abstract val userDao: UserDao

    companion object {
        private const val DATABASE_NAME = "UsersDatabase"

        fun getDatabase(ctx: Context): UsersDatabase {
            val builder = Room.databaseBuilder(ctx, UsersDatabase::class.java, DATABASE_NAME)

            return builder.build()
        }
    }
}