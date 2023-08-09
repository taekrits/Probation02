package com.example.adaprobation02.data.database.model
import com.google.gson.annotations.SerializedName

data class CCNMPovinceL(
    @SerializedName("FTPvnCode")
    val tPvnCode: String,
    @SerializedName("FNLngID")
    val nLngID: Int,
    @SerializedName("FTPvnName")
    val tName: String?
)
