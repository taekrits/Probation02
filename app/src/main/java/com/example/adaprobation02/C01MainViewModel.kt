package com.example.adaprobation02

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adaprobation02.data.database.helper.CDatabaseHelper
import com.example.adaprobation02.data.mapper.C_SETxToCCNMCompLList
import com.example.adaprobation02.data.mapper.C_SETxToCCNMCompList
import com.example.adaprobation02.data.mapper.C_SETxToCCNMDistrict
import com.example.adaprobation02.data.mapper.C_SETxToCCNMDistrictL
import com.example.adaprobation02.data.mapper.C_SETxToCCNMPovinceL
import com.example.adaprobation02.data.mapper.C_SETxToCCNMPovinces
import com.example.adaprobation02.data.mapper.C_SETxToCDownloadList
import com.example.adaprobation02.data.remote.IAdaApi
import com.example.adaprobation02.domain.CDownloadData
import com.example.adaprobation02.domain.CDownloadList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class C01MainViewModel @Inject constructor(private val api: IAdaApi ) : ViewModel() {

    var bC_CheckAllData by mutableStateOf(false)
        private set
    var bC_CheckSyncData by mutableStateOf(false)
        private set
    var bC_CheckClearOldData by mutableStateOf(false)
        private set

    private var oC_MainList = listOf<CDownloadList>()

    var oC_State by mutableStateOf(
        CDownloadData()
    )
    fun init(poContext: Context) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                val oDb = CDatabaseHelper(poContext)
                val aoStringList = oDb.C_GETxTaskDataList()
                val aoData = aoStringList.map {
                    it.C_SETxToCDownloadList()
                }
                oC_State = oC_State.copy(
                    aoDataDownloadList = aoData,
                    tDateEditText = LocalDate.now().toString()
                )
                oC_MainList = aoData
            }
        }
    }

    fun C_SETxCheckBoxAllData(pbCheck: Boolean) {
        bC_CheckAllData = pbCheck
        if (pbCheck) {
            oC_State = oC_State.copy(
                aoDataDownloadList = oC_State.aoDataDownloadList.map {
                    it.bSelect.value = true
                    it
                }
            )
        } else {
            oC_State = oC_State.copy(
                aoDataDownloadList = oC_State.aoDataDownloadList.map {
                    it.bSelect.value = false
                    it
                }
            )
        }
    }

    fun C_SETxCheckBoxSyncData(pbCheck: Boolean) {
        bC_CheckSyncData = pbCheck

    }

    fun C_SETxCheckBoxClearOldData(pbCheck: Boolean) {
        bC_CheckClearOldData = pbCheck
    }

    fun C_SETxCheckBoxListData(pbNewCheck: Boolean, paItem: CDownloadList) {
        oC_State = oC_State.copy(
            aoDataDownloadList = oC_State.aoDataDownloadList.map {
                if (it == paItem) {
                    it.bSelect.value = pbNewCheck
                }
                it
            }
        )
    }

    fun C_SETxTextFieldAndSearch(ptText: String) {
        oC_State = oC_State.copy(tSearchEditText = ptText)

        if (ptText != "") {
            oC_State = oC_State.copy(
                aoDataDownloadList = oC_MainList.filter {
                    it.tName.contains(ptText)
                }
            )
        } else {
            oC_State = oC_State.copy(
                aoDataDownloadList = oC_MainList
            )
        }
    }

    fun C_SETxDate(pdDate: String) {
        oC_State = oC_State.copy(
            tDateEditText = pdDate
        )
    }

    fun C_SETxClickDownload(poContext: Context) {
        viewModelScope.launch {
            val oDb = CDatabaseHelper(poContext)
            if(bC_CheckClearOldData){
                oDb.C_SETxDelete()
            }
            val tDate = oC_State.tDateEditText
            val dCurrentDateTime = LocalDateTime.now()
            val oFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val tFormattedDateTime = dCurrentDateTime.format(oFormatter)

            try {
                val aoSelectedList = oC_State.aoDataDownloadList.filter { it.bSelect.value }

                aoSelectedList.forEach { it ->
                    val tOUrl = it.tUri
                    val tUrl = it.tUri.split('?').first().drop(1)

                    when (tUrl) {
                        "Company/Download" -> C_GETxProcessCompanyData(tUrl, tDate, poContext)
                        "Province/Download" -> C_GETxProcessProvinceData(tUrl, tDate, poContext)
                        "District/Download" -> C_GETxProcessDistrictData(tUrl, tDate, poContext)
                        else -> println("Unknown URL: $tUrl")
                    }
                        //oDb.C_SETxDelete()
                        oDb.C_SETxUpdateTSynLast(tFormattedDateTime,tOUrl)

                    val aoStringList = oDb.C_GETxTaskDataList()
                    val aoData = aoStringList.map {
                        it.C_SETxToCDownloadList()
                    }
                    oC_State = oC_State.copy(
                        aoDataDownloadList = aoData,
                    )
                    oC_MainList = aoData



                }

            } catch (e: Exception) {
                println("Error fetching data: ${e.message}")
            }
        }
    }

    suspend fun C_GETxProcessCompanyData(ptUrl: String, ptDate: String, poContext: Context) {
        val aoResponse = withContext(Dispatchers.IO) { api.getUser(path = ptUrl, date = ptDate) }
        val oDb = CDatabaseHelper(poContext)
        if (aoResponse.isSuccessful) {
            aoResponse.body()?.let { listApi ->
                listApi.C_SETxToCCNMCompLList().let { downloadCNMCompL ->
                    oDb.C_SETxInserCCNMCompL(downloadCNMCompL)
                }
                listApi.C_SETxToCCNMCompList().let { downloadCNMComp ->
                    oDb.C_SETxInsertCCNMComp(downloadCNMComp)
                }

            }
        } else {
            println("Error processing company data: ${aoResponse.code()}")
        }
    }

    suspend fun C_GETxProcessProvinceData(ptUrl: String, ptDate: String,poContext: Context) {
        val aoResponse = withContext(Dispatchers.IO) { api.getPovince(path = ptUrl, date = ptDate) }
        val oDb = CDatabaseHelper(poContext)
        if (aoResponse.isSuccessful) {
            aoResponse.body()?.let {
                it.C_SETxToCCNMPovinces().let {
                    oDb.C_SETxInsertCCNMPovince(it)
                }
                it.C_SETxToCCNMPovinceL().let {
                    oDb.C_SETxInsertCCNMPovinceL(it)
                }
            }
        } else {
            println("Error processing province data: ${aoResponse.code()}")
        }
    }

    suspend fun C_GETxProcessDistrictData(ptUrl: String, ptDate: String,poContext: Context) {
        val aoResponse = withContext(Dispatchers.IO) { api.getDistrict(path = ptUrl, date = ptDate) }
        val oDb = CDatabaseHelper(poContext)
        if (aoResponse.isSuccessful) {
            aoResponse.body()?.let {
                it.C_SETxToCCNMDistrict().let {
                    oDb.C_SETxInsertCCNMDistrict(it)
                }
                it.C_SETxToCCNMDistrictL().let {
                    oDb.C_SETxInsertCCNMDistrictL(it)
                }
            }
        } else {
            println("Error processing district data: ${aoResponse.code()}")
        }
    }

}

