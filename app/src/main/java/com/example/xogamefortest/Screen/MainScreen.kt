package com.example.xogamefortest.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.xogamefortest.Database.GameDB

import com.example.xogamefortest.Database.Game_Database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavHostController) {
    var SizeTable by remember { mutableStateOf("0") }
    var DetailBT by remember { mutableStateOf("") }

    val context = LocalContext.current // ดึง Context ของปัจจุบันมาใช้งาน
    val GameRoom = Game_Database.getInstance(context).GameDao()
    val GameHistory: List<GameDB> by GameRoom.getAllGames().observeAsState(emptyList())


        Column (modifier = Modifier
            .fillMaxSize()
            .padding(top = 0.dp, start = 30.dp, end = 30.dp, bottom = 30.dp),
            verticalArrangement = Arrangement.Center, // จัดตำแหน่งแนวตั้งให้กึ่งกลาง
            horizontalAlignment = Alignment.CenterHorizontally,){
//            ช่องใส่เลข
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                ) {
                Column(  modifier = Modifier
                    .align(Alignment.Center)
                    , verticalArrangement = Arrangement.Center, // จัดตำแหน่งแนวตั้งให้กึ่งกลาง
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = "Game XO", style = MaterialTheme.typography.bodyLarge)
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp),
                    value = SizeTable,
                    onValueChange = { SizeTable = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text("จำนวนช่วงที่ต้องการไม่น้อยกว่า 3") }
                )

//            ปุ่มเริ่ม
                Button(onClick = {

                    if (SizeTable.toInt() >= 3  ){
                        navController.navigate("GameScreen/$SizeTable")
                    }else{DetailBT = "โปรดใส่จำนวนอย่างน้อย 3 "} }) {
                    Text(text = "เริ่มเล่น")
                }

//          ดักError
                Text(text = DetailBT)
                }
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                LazyColumn(modifier = Modifier
                .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp)) {
                itemsIndexed(GameHistory) { index, detail -> // ตรวจสอบให้แน่ใจว่า 'debts' เป็น List<Debt>
                    ListHistory(detail = detail,navController) // ส่ง 'debt' เข้าไปใน Composable ที่ถูกต้อง

                }
            }}


        }


}

@Composable
fun ListHistory(detail: GameDB,navController: NavHostController) {
    val coroutineScope = rememberCoroutineScope()

    OutlinedCard (modifier = Modifier.clickable {
        navController.navigate("HistoryScreen/${detail.DBTime}/${detail.select}/${detail.timeselect}/${detail.result}")
    }){
        Row(modifier = Modifier
            .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically) {

            Column(modifier = Modifier
                .fillMaxSize()
                .weight(0.5F)
                .padding(vertical = 8.dp)
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp)){
                Text(text = "${detail.result}", style = MaterialTheme.typography.bodySmall)
                Text(text = "${detail.DBTime}", style = MaterialTheme.typography.bodySmall)

            }



        }

//            Text(text = "Debt: ${debt.DBDebt}", style = MaterialTheme.typography.bodyMedium)

    }

}
