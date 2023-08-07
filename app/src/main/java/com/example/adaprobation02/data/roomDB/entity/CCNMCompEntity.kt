package com.example.adaprobation02.data.roomDB.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TCNMComp")
data class CCNMCompEntity(
    @PrimaryKey
    val FTCmpCode: String,

    val FTCmpTel: String?,

    val FTCmpFax: String?,

    val FTBchcode: String?,

    val FTCmpWhsInOrEx: String?,

    val FTCmpRetInOrEx: String?,

    val FTCmpEmail: String?,

    val FTRteCode: String,

    val FTVatCode: String,

    val FDLastUpdOn: String?,

    val FTLastUpdBy: String?,

    val FDCreateOn: String?,

    val FTCreateBy: String?
)
