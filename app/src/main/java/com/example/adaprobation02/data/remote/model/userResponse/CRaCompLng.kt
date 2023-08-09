package com.example.adaprobation02.data.remote.model.userResponse


import com.google.gson.annotations.SerializedName

data class CRaCompLng(
    @SerializedName("rnLngID")
    val nLngID: Int,
    @SerializedName("rtCmpCode")
    val tCmpCode: String,
    @SerializedName("rtCmpDirector")
    val tCmpDirector: String,
    @SerializedName("rtCmpName")
    val tCmpName: String,
    @SerializedName("rtCmpShop")
    val tCmpShop: String
)