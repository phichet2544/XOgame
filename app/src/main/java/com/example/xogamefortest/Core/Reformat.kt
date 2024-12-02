package com.example.xogamefortest.Core

fun ToMatrix(position:Int,size:Int): Pair<Int, Int> {
    var index = 0
    for (i in 0 until size) {
        for (j in 0 until size) {
            if (index == position){
                return Pair(i,j)
            }
            index++
        }

        }
 return Pair(0,0)
}

fun toNumber(x:Int,y:Int,size:Int):Int{
    var counter = 1
    for (i in 0 until size) {
        for (j in 0 until size) {
            if (i == x && j == y) {
                return counter
            }
            counter++  // เพิ่มค่าของ matrix ในแต่ละตำแหน่ง
        }
    }
    return counter

}