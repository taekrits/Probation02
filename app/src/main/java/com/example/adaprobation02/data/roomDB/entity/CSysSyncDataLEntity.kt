package com.example.adaprobation02.data.roomDB.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TSysSyncData_L")
data class CSysSyncDataLEntity(
    @PrimaryKey(autoGenerate = false)
    val FNSynSeqNo: Int,

    val FNLngID: Int,

    val FTSynName: String?,

    val FTSynRmk: String?

)
