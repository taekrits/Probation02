package com.example.adaprobation02

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adaprobation02.data.helper.CDatabaseHelper
import com.example.adaprobation02.data.mapper.mapToCDownloadList
import com.example.adaprobation02.domain.CDownloadData
import com.example.adaprobation02.domain.CDownloadList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@RequiresApi(Build.VERSION_CODES.O)
class C01MainViewModel : ViewModel() {

    var bC_CheckAllData by mutableStateOf(false)
        private set
    var bC_CheckSyncData by mutableStateOf(false)
        private set
    var bC_CheckClearOldData by mutableStateOf(false)
        private set

    private var oMainList = listOf<CDownloadList>()

    var oC_State by mutableStateOf(
        CDownloadData()
    )

    fun init(context: Context) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                val db = CDatabaseHelper(context)
                val stringList = db.C_GETxTaskDataList()
                val data = stringList.map {
                    it.mapToCDownloadList()
                }
                oC_State = oC_State.copy(
                    aDataDownloadList = data
                )
                oMainList = data
            }
        }
    }

    fun C_SETxCheckBoxAllData(bCheck: Boolean) {
        bC_CheckAllData = bCheck
        if (bCheck) {
            oC_State = oC_State.copy(
                aDataDownloadList = oC_State.aDataDownloadList.map {
                    it.bSelect.value = true
                    it
                }
            )
        } else {
            oC_State = oC_State.copy(
                aDataDownloadList = oC_State.aDataDownloadList.map {
                    it.bSelect.value = false
                    it
                }
            )
        }
    }

    fun onClickDownload() {

        val listURL = oC_State.aDataDownloadList
        listURL.forEach {
            if (it.bSelect.value) {
                println(it.tUri)
            }
        }

        val list = listURL.filter { it.bSelect.value }.map { it.tUri }
        println(list)

    }

    fun C_SETxCheckBoxSyncData(bCheck: Boolean) {
        bC_CheckSyncData = bCheck

    }

    fun C_SETxCheckBoxClearOldData(bCheck: Boolean) {
        bC_CheckClearOldData = bCheck
    }

    fun C_SETxCheckBoxListData(bNewCheck: Boolean, aItem: CDownloadList) {
//        item.bSelect.value = newCheck
        oC_State = oC_State.copy(
            aDataDownloadList = oC_State.aDataDownloadList.map {
                if (it == aItem) {
                    it.bSelect.value = bNewCheck
                }
                it
            }
        )
    }

    fun C_SETxTextFieldAndSearch(tText: String) {
        oC_State = oC_State.copy(tSearchEditText = tText)

        if (tText != "") {
            oC_State = oC_State.copy(
                aDataDownloadList = oMainList.filter {
                    it.tName.contains(tText)
                }
            )
        } else {
            oC_State = oC_State.copy(
                aDataDownloadList = oMainList
            )
        }
    }

    fun C_SETxDate(dDate: String) {
        oC_State = oC_State.copy(
            tDateEditText = dDate
        )
    }
}

