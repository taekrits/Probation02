package com.example.adaprobation02.data.mapper

import androidx.compose.runtime.mutableStateOf
import com.example.adaprobation02.data.model.CDataDownloadListDB
import com.example.adaprobation02.domain.CDownloadList

fun CDataDownloadListDB.mapToCDownloadList(): CDownloadList {
    return CDownloadList(
        bSelect = mutableStateOf(false),
        tName = this.tSynName,
        tDateTime = this.tSynLast,
        tUri = this.tSynUriDwn
    )
}
