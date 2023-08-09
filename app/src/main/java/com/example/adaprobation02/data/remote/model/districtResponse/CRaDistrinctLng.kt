package com.example.adaprobation02.data.remote.model.districtResponse


import com.google.gson.annotations.SerializedName

data class CRaDistrinctLng(
    @SerializedName("rnLngID")
    val nLngID: Int,
    @SerializedName("rtDstCode")
    val tDstCode: String,
    @SerializedName("rtDstName")
    val tDstName: String
)