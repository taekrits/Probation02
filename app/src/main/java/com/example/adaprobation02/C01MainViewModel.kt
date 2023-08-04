package com.example.adaprobation02

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.adaprobation02.domain.CDownloadData
import com.example.adaprobation02.domain.CDownloadList
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class C01MainViewModel : ViewModel() {

    var bCheckAllData by mutableStateOf(false)
        private set
    var bCheckSyncData by mutableStateOf(false)
        private set
    var bCheckClearOldData by mutableStateOf(false)
        private set



    val aMockList = listOf(
        CDownloadList(
            bSelect = mutableStateOf(false),
            tName = "บริษัท",
            tDateTime = "2023-08-02 11:44:08"
        ),
        CDownloadList(
            bSelect = mutableStateOf(false),
            tName = "สาขา",
            tDateTime = "2023-08-02 11:43:04"
        ),
        CDownloadList(
            bSelect = mutableStateOf(false),
            tName = "ผู้ใช้",
            tDateTime = "2023-08-02 11:41:28"
        ),
    )
    var oState by mutableStateOf(
        CDownloadData(
            tSearchEditText = "",
            tDateEditText = LocalDate.now().toString(),
            aDataDownloadList = aMockList
        )
    )

    fun C_SETxCheckBoxAllData(bCheck: Boolean) {
        bCheckAllData = bCheck
        if (bCheck) {
            oState = oState.copy(
                aDataDownloadList = oState.aDataDownloadList.map {
                    it.bSelect.value = true
                    it
                }
            )
        } else {
            oState = oState.copy(
                aDataDownloadList = oState.aDataDownloadList.map {
                    it.bSelect.value = false
                    it
                }
            )
        }
    }

    fun C_SETxCheckBoxSyncData(bCheck: Boolean) {
        bCheckSyncData = bCheck

    }

    fun C_SETxCheckBoxClearOldData(bCheck: Boolean) {
        bCheckClearOldData = bCheck
    }

    fun C_SETxCheckBoxListData(bNewCheck: Boolean, aItem: CDownloadList) {
//        item.bSelect.value = newCheck
        oState = oState.copy(
            aDataDownloadList = oState.aDataDownloadList.map {
                if (it == aItem) {
                    it.bSelect.value = bNewCheck
                }
                it
            }
        )
    }

    fun C_SETxTextFieldAndSearch(tText: String) {
        oState = oState.copy(tSearchEditText = tText)

        if (tText != "") {
            oState = oState.copy(
                aDataDownloadList = oState.aDataDownloadList.filter {
                    it.tName.contains(tText)
                }
            )
        } else {
            oState = oState.copy(
                aDataDownloadList = aMockList
            )
        }
    }

    fun C_SETxDate(dDate: String) {
        oState = oState.copy(
            tDateEditText = dDate
        )
    }
}

