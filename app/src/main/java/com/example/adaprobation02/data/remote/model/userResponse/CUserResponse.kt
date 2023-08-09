package com.example.adaprobation02.data.remote.model.userResponse


import com.google.gson.annotations.SerializedName

data class CUserResponse(
    @SerializedName("roItem")
    val aoItem: CRoItem,
    @SerializedName("rtCode")
    val tCode: String,
    @SerializedName("rtDesc")
    val tDesc: String
)