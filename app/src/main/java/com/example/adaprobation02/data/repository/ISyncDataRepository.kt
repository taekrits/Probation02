package com.example.adaprobation02.data.repository

import com.example.adaprobation02.data.model.CDataDownloadListDB

interface ISyncDataRepository {

    //interface ไม่มีก็ได้ ถ้ามีจะดีในตอนทำ unit test
    fun C_GETxTaskDataList(): List<CDataDownloadListDB>

}