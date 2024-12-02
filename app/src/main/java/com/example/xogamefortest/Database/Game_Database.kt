package com.example.xogamefortest.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [GameDB::class], version = 1)
abstract class Game_Database : RoomDatabase() {
    abstract fun GameDao(): Game_DAO

    companion object {
        @Volatile
        private var INSTANCE: Game_Database? = null

        fun getInstance(context: Context): Game_Database {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Game_Database::class.java,
                    "Game_Database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}