package com.example.adaprobation02

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.text.Editable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.adaprobation02.domain.CDownloadList
import com.example.adaprobation02.ui.theme.AdaProbation02Theme
import com.example.adaprobation02.ui.theme.Green
import com.example.adaprobation02.ui.theme.GreenDark
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.util.Calendar
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AdaProbation02Theme {
                // A surface container using the 'background' color from the theme
                C01MainScreen()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun C01MainScreen(
) {

    val oContext = LocalContext.current
    val oViewModel: C01MainViewModel = hiltViewModel()
    val oCalendar = Calendar.getInstance()
    val oYear = oCalendar.get(Calendar.YEAR)
    val oMonth = oCalendar.get(Calendar.MONTH)
    val oDay = oCalendar.get(Calendar.DAY_OF_MONTH)

    val oDatePickerDialog =
        DatePickerDialog(oContext, { _, selectedYear, selectedMonth, selectedDay ->
            val adjustedMonth = "${selectedMonth + 1}".padStart(2, '0')
            val adjustedDay = "$selectedDay".padStart(2, '0')
            val tSelectedDate = "$selectedYear-$adjustedMonth-$adjustedDay"
            val oSelectedDateEditable: Editable =
                Editable.Factory.getInstance().newEditable(tSelectedDate)
            oViewModel.C_SETxDate(oSelectedDateEditable.toString())
        }, oYear, oMonth, oDay)


    LaunchedEffect(key1 = Unit){
        oViewModel.init(poContext = oContext)
    }

    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(GreenDark)
        ) {
            Image(
                painter = painterResource(id = R.drawable.p01back),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(CenterVertically)
                    .size(30.dp)
            )
            TextField(
                value = oViewModel.oC_State.tSearchEditText,
                onValueChange = { ptNewText ->

                    oViewModel.C_SETxTextFieldAndSearch(ptNewText)

                },
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White,
                    lineHeight = 1.sp
                ),
                placeholder = {
                    Text(
                        text = "ค้นหาข้อมูลดาวน์โหลด",
                        fontSize = 20.sp
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                ),

                )
            Image(
                painter = painterResource(id = R.drawable.p01search),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(CenterVertically)
                    .size(30.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.p01more),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(CenterVertically)
                    .size(30.dp)
            )
        }
        Box(
            modifier = Modifier
                .padding(horizontal = 3.dp)
                .padding(top = 3.dp)
                .fillMaxWidth()
                .background(Green)
        )
        {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = oViewModel.bC_CheckAllData,
                    onCheckedChange = { pbNewCheck ->
                        oViewModel.C_SETxCheckBoxAllData(pbNewCheck)
                    },
                )
                Text(
                    text = "เลือกทั้งหมด",
                    fontSize = 16.sp,
                    color = Color.White,
                )
            }

            Row(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = oViewModel.bC_CheckSyncData,
                    onCheckedChange = { pbNewCheck ->
                        oViewModel.C_SETxCheckBoxSyncData(pbNewCheck)
                    },
                )
                Text(
                    text = "ข้อมูลซิงค์ล่าสุด",
                    fontSize = 16.sp,
                    color = Color.White,
                )
            }

        }
        if (!oViewModel.bC_CheckSyncData) {
            C01BarSyncData(
                poViewModel = oViewModel,
                poClick = { oDatePickerDialog.show() }
            )
        }
        Spacer(modifier = Modifier.height(3.dp))

        LazyColumn(
            modifier = Modifier.padding(bottom = 50.dp)
        ) {
            itemsIndexed(oViewModel.oC_State.aoDataDownloadList) { index, item ->
                C01DownloadListLayout(
                    paItem = item,
                    piIndex = index,
                    poViewModel = oViewModel
                )
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 3.dp)
    ) {
        Button(
            onClick = {

                oViewModel.C_SETxClickDownload(poContext = oContext)

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 3.dp)
                .align(BottomCenter),
            colors = ButtonDefaults.buttonColors(GreenDark),
            shape = RoundedCornerShape(0.dp)
        ) {
            Text(
                text = "ดาวน์โหลด",
                color = Color.White,
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun C01BarSyncData(poViewModel: C01MainViewModel, poClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(horizontal = 3.dp)
            .fillMaxWidth()
            .background(Green)
    )
    {
        Box(

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = poViewModel.bC_CheckClearOldData,
                    onCheckedChange = { newCheck ->
                        poViewModel.C_SETxCheckBoxClearOldData(newCheck)
                    },
                )
                Text(
                    text = "ล้างข้อมูลเก่า",
                    fontSize = 16.sp,
                    color = Color.White,
                )
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 8.dp)

        ) {
            Row(
                modifier = Modifier.clickable {
                    poClick.invoke()
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    modifier = Modifier
                        .width(160.dp),
                    enabled = false,
                    value = poViewModel.oC_State.tDateEditText,
                    onValueChange = {

                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        disabledTextColor = Color.White
                    ),
                    placeholder = {
                        Text(
                            text = LocalDate.now().toString(),
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    },
                    trailingIcon = {
                        Image(

                            painter = painterResource(id = R.drawable.p01calendar),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(Color.White),
                        )
                    },

                    )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun C01DownloadListLayout(paItem: CDownloadList, piIndex: Int, poViewModel: C01MainViewModel) {
    Row(
        modifier = Modifier
            .padding(horizontal = 3.dp)
            .fillMaxWidth()
            .background(
                if (piIndex % 2 == 0) {
                    colorResource(id = R.color.green_light)
                } else {
                    colorResource(id = R.color.white)
                }


            ),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Checkbox(
            checked = paItem.bSelect.value,
            onCheckedChange = { newCheck ->
                poViewModel.C_SETxCheckBoxListData(newCheck, paItem)
            }
        )
        Text(
            modifier = Modifier.weight(1f),
            text = paItem.tName,
            fontSize = 14.sp
        )
        Text(
            modifier = Modifier.weight(1f),
            text = paItem.tDateTime,
            fontSize = 12.sp
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun C01MainScreenPreview() {
    AdaProbation02Theme {
        C01MainScreen()
    }
}

