package com.example.xogamefortest.Database

import androidx.lifecycle.LiveData


class Game_Repositcry (private val dao : Game_DAO) {
        val debtdb : LiveData<List<GameDB>> = dao.getAllGames()



        suspend fun Insert(gameDB: GameDB){
            return dao.insertGame(gameDB)
        }
        suspend fun Delete(gameDB: GameDB){
            return dao.deleteGame(gameDB)
        }

       }
