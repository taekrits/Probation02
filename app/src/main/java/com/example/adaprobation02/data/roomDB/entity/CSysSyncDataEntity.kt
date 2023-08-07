package com.example.adaprobation02.data.roomDB.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TSysSyncData")
data class CSysSyncDataEntity(
    @PrimaryKey
    val FNSynSeqNo: Int,

    val FTSynGroup: String?,

    val FTSynTable: String?,

    val FTSynTable_L: String?,

    val FTSynType: String?,

    val FDSynLast: String?,

    val FNSynSchedule: Int?,

    val FTSynStaUse: String?,

    val FTSynUriDwn: String?,

    val FTSynUriUld: String?
)
