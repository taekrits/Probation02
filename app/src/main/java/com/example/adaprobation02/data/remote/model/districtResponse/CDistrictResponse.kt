package com.example.adaprobation02.data.remote.model.districtResponse

import com.google.gson.annotations.SerializedName


data class CDistrictResponse(
    @SerializedName("roItem")
    val aoItem: CRoItem,
    @SerializedName("rtCode")
    val tCode: String,
    @SerializedName("rtDesc")
    val tDesc: String
)