package com.example.adaprobation02.data.database.model
import com.google.gson.annotations.SerializedName

data class CCNMDistrictL(
    @SerializedName("FTDstCode")
    val tDstCode: String,
    @SerializedName("FNLngID")
    val tLngID: Int,
    @SerializedName("FTDstName")
    val tDstName: String
)
