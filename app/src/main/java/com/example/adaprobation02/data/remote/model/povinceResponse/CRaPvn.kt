package com.example.adaprobation02.data.remote.model.povinceResponse


import com.google.gson.annotations.SerializedName

data class CRaPvn(
    @SerializedName("rdCreateOn")
    val tCreateOn: String,
    @SerializedName("rdLastUpdOn")
    val tLastUpdOn: String,
    @SerializedName("rtCreateBy")
    val tCreateBy: String,
    @SerializedName("rtLastUpdBy")
    val tLastUpdBy: String,
    @SerializedName("rtPvnCode")
    val tPvnCode: String,
    @SerializedName("rtPvnLatitude")
    val tPvnLatitude: String,
    @SerializedName("rtPvnLongitude")
    val tPvnLongitude: String,
    @SerializedName("rtZneCode")
    val tZneCode: String
)