package com.example.adaprobation02.data.database.model

import com.google.gson.annotations.SerializedName

data class CCNMDistrict(
    @SerializedName("FTDstCode")
    val tDstCode: String,
    @SerializedName("FTDstPost")
    val tDstPost: String?,
    @SerializedName("FTPvnCode")
    val tPvnCode: String?,
    @SerializedName("FTDstLatitude")
    val tDstLatitude: String?,
    @SerializedName("FTDstLongitude")
    val tDstLongitude: String?,
    @SerializedName("FDLastUpdOn")
    val tLastUpdOn: String?,
    @SerializedName("FTLastUpdBy")
    val tLastUpdBy: String?,
    @SerializedName("FDCreateOn")
    val tCreateOn: String?,
    @SerializedName("FTCreateBy")
    val tCreateBy: String?
)
