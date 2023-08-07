package com.example.adaprobation02.data.repository

import com.example.adaprobation02.data.model.CDataDownloadListDB

interface ISyncDataRepository {
    fun C_GETxTaskDataList(): List<CDataDownloadListDB>

}