package com.example.adaprobation02.data.remote.model.povinceResponse


import com.google.gson.annotations.SerializedName

data class CPovinceResponse(
    @SerializedName("roItem")
    val aoItem: CRoItem,
    @SerializedName("rtCode")
    val tCode: String,
    @SerializedName("rtDesc")
    val tDesc: String
)