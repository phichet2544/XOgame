package com.example.xogamefortest.Core

import androidx.compose.runtime.MutableState

fun CoreGame(matrix: List<MutableList<MutableState<Int>>>, player: Int): String {
    val size = matrix.size
    val winP = if (player ==1){"ผู้เล่นชนะ"}else{"บอทชนะ"}

    for (i in 0 until size) {
        if ((0 until size).all { j -> matrix[i][j].value == player }) return winP
        if ((0 until size).all { j -> matrix[j][i].value == player }) return winP
    }


    if ((0 until size).all { i -> matrix[i][i].value == player }) return winP
    if ((0 until size).all { i -> matrix[i][size - 1 - i].value == player }) return winP

    if (matrix.all { row -> row.all { it.value != 0 } }){
        return "เสมอ"
    }
    return "Not"
}