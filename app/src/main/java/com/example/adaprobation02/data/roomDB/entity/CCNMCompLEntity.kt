package com.example.adaprobation02.data.roomDB.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TCNMComp_L")
data class CCNMCompLEntity(
    @ColumnInfo(name = "FTCmpCode")
    val FTCmpCode: String,

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "FNLngID")
    val FNLngID: Int,

    @ColumnInfo(name = "FTCmpName")
    val FTCmpName: String?,

    @ColumnInfo(name = "FTCmpShop")
    val FTCmpShop: String?,

    @ColumnInfo(name = "FTCmpDirector")
    val FTCmpDirector: String?
)
