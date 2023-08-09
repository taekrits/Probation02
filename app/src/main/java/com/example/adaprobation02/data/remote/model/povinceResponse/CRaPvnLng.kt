package com.example.adaprobation02.data.remote.model.povinceResponse


import com.google.gson.annotations.SerializedName

data class CRaPvnLng(
    @SerializedName("rnLngID")
    val nLngID: Int,
    @SerializedName("rtPvnCode")
    val tPvnCode: String,
    @SerializedName("rtPvnName")
    val tPvnName: String
)