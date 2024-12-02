package com.example.xogamefortest.Screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.xogamefortest.Core.AI
import com.example.xogamefortest.Core.CoreGame
import com.example.xogamefortest.Core.ToMatrix
import com.example.xogamefortest.Core.toNumber
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.sqrt

@Composable
fun HistoryScreen(navController: NavHostController, time:String,select:String,timeselect:String,result:String) {
    Column {
        val coroutineScope = rememberCoroutineScope()
        var DetailBT by remember { mutableStateOf("") }

        val replayGame = timeselect.split(",").toTypedArray()
        val StoA = select.split(",").toTypedArray()
        val numALL = StoA.size
        val num = sqrt(numALL.toDouble()).toInt()
        val matrix = remember { mutableStateListOf(*Array(num.toInt()) { mutableListOf(*Array(num.toInt()) { mutableStateOf(0) }) }) }

        LaunchedEffect(Unit) {
            var index = 0
            for (row in 0 until num.toInt()) {
                for (col in 0 until num.toInt()) {
                    if (index < numALL) {
                        matrix[row][col].value =
                            StoA[index].trim().toInt() // นำค่าจาก 1D array ไปใส่ใน matrix
                        index++
                    }
                }
            }
        }



        Text(text = "เวลา: ${time}",style = MaterialTheme.typography.titleLarge)
        Text(text = "ใครชนะ: ${result}",style = MaterialTheme.typography.titleLarge)

        Button(onClick = {

            coroutineScope.launch {
                for (i in 0 until num.toInt()) {
                    for (j in 0 until num.toInt()) {
                        matrix[i][j].value = 0
                    }
                }



                for (x in 0 until replayGame.size) {
                    delay(500)
                    val chagenumber = ToMatrix(replayGame[x].trim().toInt()-1,num)
                    DetailBT +="${chagenumber.first},${chagenumber.second}   "
                        matrix[chagenumber.first][chagenumber.second].value = StoA[replayGame[x].trim().toInt()-1].trim().toInt()


                }    }
        }) {
            Text(text = "Replay")
        }
        LazyVerticalGrid(
        columns = GridCells.Fixed(num.toInt()),
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(1f)
    ) {
        items(numALL) { index ->
            val Amatrix = ToMatrix(index,num.toInt())
            Button(
                onClick = {

                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White, // เปลี่ยนสีพื้นหลัง
                    contentColor = Color.White // สีของข้อความในปุ่ม
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .padding(1.dp)
                    .border(1.dp, Color.Black, RoundedCornerShape(16.dp))

            ) {
                PictureBT(matrix[Amatrix.first][Amatrix.second].value)
                Text(text = "${Amatrix.first} + ${Amatrix.second}")
                Text(text = matrix[Amatrix.first][Amatrix.second].value.toString())
            }
        }
    }

    }
}
