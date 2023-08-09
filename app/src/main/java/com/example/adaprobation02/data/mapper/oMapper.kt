package com.example.adaprobation02.data.mapper

import androidx.compose.runtime.mutableStateOf
import com.example.adaprobation02.data.database.model.CDataDownloadListDB
import com.example.adaprobation02.domain.CDownloadList

fun CDataDownloadListDB.C_SETxToCDownloadList(): CDownloadList {
    return CDownloadList(
        bSelect = mutableStateOf(false),
        tName = this.tSynName,
        tDateTime = this.tSynLast,
        tUri = this.tSynUriDwn
    )
}
