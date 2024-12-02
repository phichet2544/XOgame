package com.example.xogamefortest.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface Game_DAO {
    @Query("SELECT * FROM History_table")
    fun getAllGames(): LiveData<List<GameDB>>

    @Insert
    suspend fun insertGame(game: GameDB)

    @Delete
    suspend fun deleteGame(game: GameDB)
}