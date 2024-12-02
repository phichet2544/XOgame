package com.example.xogamefortest.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "History_table")
data class GameDB(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "Time") var DBTime: String?,
    @ColumnInfo(name = "select") val select: String?,
    @ColumnInfo(name = "timeselect") val timeselect: String?,
    @ColumnInfo(name = "result") val result: String?
)
