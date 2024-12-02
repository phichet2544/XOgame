package com.example.xogamefortest.Core

import androidx.compose.runtime.MutableState
import kotlin.random.Random

fun AI(matrix: MutableList<MutableList<MutableState<Int>>>): Pair<Int, Int>{
    val EmptyPositions = mutableListOf<Pair<Int, Int>>()
    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            if (matrix[i][j].value == 0) {
                EmptyPositions.add(Pair(i, j))
            }
        }
    }
    if (EmptyPositions.isNotEmpty()  ){
    val randomPosition = EmptyPositions[Random.nextInt(EmptyPositions.size)]
        return randomPosition
    }
    return Pair(0,0)
}


