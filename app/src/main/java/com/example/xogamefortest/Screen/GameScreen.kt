package com.example.xogamefortest.Screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.graphics.component4
import androidx.navigation.NavHostController
import com.example.xogamefortest.Core.AI
import com.example.xogamefortest.Core.CoreGame
import com.example.xogamefortest.Core.ToMatrix
import com.example.xogamefortest.Core.toNumber
import com.example.xogamefortest.Database.GameDB
import com.example.xogamefortest.Database.Game_Database
import com.example.xogamefortest.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Composable
fun GameScreen(navController: NavHostController,num:String){
    var test by remember { mutableStateOf("") }
    val numAll = num.toInt()*num.toInt()
    val matrix = remember { mutableStateListOf(*Array(num.toInt()) { mutableListOf(*Array(num.toInt()) { mutableStateOf(0) }) })}
    var showDialog by remember { mutableStateOf(false) }
    var checkwin by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val GameRoom = Game_Database.getInstance(context).GameDao()
    val coroutineScope = rememberCoroutineScope()

    var recordgame by remember { mutableStateOf(mutableListOf<String>()) }
    var recordSelect by remember { mutableStateOf(mutableListOf<String>()) }
//    val recordSelect = StringBuilder() //จดตำแหน่งการเลือก

    val currentDateTime = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("วันที่: dd เดือน: MM ปี: yyyy เวลา: HH:mm:ss")
    val formattedDateTime = currentDateTime.format(formatter)


    Column {


        Text(text = "จำนวนตาราง ${num}")
//        matrix.forEach { row ->
//       Text(text = row.joinToString(" "))
//        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(num.toInt()),
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        ) {

            items(numAll) { index ->
                val Amatrix = ToMatrix(index,num.toInt())
                Button(
                    onClick = {
                            if (matrix[Amatrix.first][Amatrix.second].value ==0 ){
                            matrix[Amatrix.first][Amatrix.second].value = 1
                            test = CoreGame(matrix,1)
                            recordSelect.add(toNumber(Amatrix.first,Amatrix.second,num.toInt()).toString())
                                if (test == "ผู้เล่นชนะ"||test == "เสมอ"){
                                    checkwin = true
                                }else{
                                    coroutineScope.launch {
                                        showDialog = true // แสดง Dialog หลังจากดีเลย์
                                        val AIclick = AI(matrix)
                                        delay(1000) // ดีเลย์ 2 วินาที
                                        showDialog = false
                                        matrix[AIclick.first][AIclick.second].value = 2
                                        test = CoreGame(matrix,2)
                                        recordSelect.add(toNumber(AIclick.first,AIclick.second,num.toInt()).toString())
                                        if (test == "บอทชนะ"){
                                            checkwin = true
                                        }
                                    }
                                }

                            }
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



        if (showDialog) {
            Dialog(onDismissRequest = { showDialog = false }) {
                Text(text = "AI.....", modifier = Modifier.padding(16.dp))
            }
        }
        if (checkwin) {
            AlertDialog(
                onDismissRequest = { },
                title = { Text(text = "") },
                text = {
                    Text(text = test, fontSize = 18.sp)
                },
                confirmButton = {
                    Button(onClick = {
                        for (i in 0 until num.toInt()) {
                            for (j in 0 until num.toInt()) {
                                matrix[i][j].value = 0
                            }
                        }
                        recordgame.clear()
                        recordSelect.clear()

                         checkwin = false
                    }) {
                        Text(text = "เริ่มเกมใหม่")
                    }

                    Button(onClick = {
                        for (i in 0 until num.toInt()) {
                            for (j in 0 until num.toInt()) {
                                recordgame.add(matrix[i][j].value.toString())
                                matrix[i][j].value = 0
                            }
                        }

                        Log.d("Debug", "recordgame: $recordgame")
                        Log.d("Debug", "recordSelect: $recordSelect")
                        Log.d("Debug", "formattedDateTime: $formattedDateTime")
                        Log.d("Debug", "test: $test")
                        coroutineScope.launch(Dispatchers.IO) {
                            GameRoom.insertGame(
                                GameDB(
                                    select = recordgame.joinToString(", "),
                                    timeselect = recordSelect.joinToString(", "),
                                    DBTime = formattedDateTime,
                                    uid = 0,
                                    result = test
                                )
                            )
                        recordgame.clear()
                        recordSelect.clear()
                        }
                        navController.navigate("MainScreen")
                        test = ""
                        checkwin = false
                    }) {
                        Text(text = "บันทึกและออก")
                    }

                }
            )
            }
        }



}



@Composable
fun PictureBT(num:Int):Unit {
    val image: Painter = painterResource(id = R.drawable.emtry)
    val image2: Painter = painterResource(id = R.drawable.circle)
    val image3: Painter = painterResource(id = R.drawable.cross)

    return when (num) {
        0 -> {
            Image(painter = image, contentDescription = "",modifier = Modifier.fillMaxSize())
        }
        1 -> {
            Image(painter = image2, contentDescription = "",modifier = Modifier.fillMaxSize())
        }
        2 -> {
            Image(painter = image3, contentDescription = "",modifier = Modifier.fillMaxSize())}
        else -> {}
    }
}


