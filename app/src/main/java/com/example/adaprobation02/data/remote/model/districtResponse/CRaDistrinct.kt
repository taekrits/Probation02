package com.example.adaprobation02.data.remote.model.districtResponse


import com.google.gson.annotations.SerializedName

data class CRaDistrinct(
    @SerializedName("rdCreateOn")
    val tCreateOn: String,
    @SerializedName("rdLastUpdOn")
    val tLastUpdOn: String,
    @SerializedName("rtCreateBy")
    val tCreateBy: String,
    @SerializedName("rtDstCode")
    val tDstCode: String,
    @SerializedName("rtDstLatitude")
    val tDstLatitude: String,
    @SerializedName("rtDstLongitude")
    val tDstLongitude: String,
    @SerializedName("rtDstPost")
    val tDstPost: String,
    @SerializedName("rtLastUpdBy")
    val tLastUpdBy: String,
    @SerializedName("rtPvnCode")
    val tPvnCode: String
)