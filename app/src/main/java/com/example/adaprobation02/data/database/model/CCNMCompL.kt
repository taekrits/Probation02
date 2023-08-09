package com.example.adaprobation02.data.database.model

import com.google.gson.annotations.SerializedName

data class CCNMCompL(
    @SerializedName("FTCmpCode")
    val tCmpCode: String,
    @SerializedName("FNLngID")
    val tLngID: Int,
    @SerializedName("FTCmpName")
    val tCmpName: String?,
    @SerializedName("FTCmpShop")
    val tCmpShop: String?,
    @SerializedName("FTCmpDirector")
    val tCmpDirector: String?
)
