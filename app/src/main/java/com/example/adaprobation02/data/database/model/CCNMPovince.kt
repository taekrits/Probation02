package com.example.adaprobation02.data.database.model
import com.google.gson.annotations.SerializedName

data class CCNMPovince(
    @SerializedName("FTPvnCode")
    val tPvnCode: String,
    @SerializedName("FTZneCode")
    val tZneCode: String?,
    @SerializedName("FTPvnLatitude")
    val tPvnLatitude: String?,
    @SerializedName("FTPvnLongitude")
    val tPvnLongitude: String?,
    @SerializedName("FDLastUpdOn")
    val tLastUpdOn: String?,
    @SerializedName("FTLastUpdBy")
    val tLastUpdBy: String?,
    @SerializedName("FDCreateOn")
    val tCreateOn: String?,
    @SerializedName("FTCreateBy")
    val tCreateBy: String?
)
