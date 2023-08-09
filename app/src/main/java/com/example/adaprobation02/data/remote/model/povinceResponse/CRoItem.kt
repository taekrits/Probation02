package com.example.adaprobation02.data.remote.model.povinceResponse


import com.google.gson.annotations.SerializedName

data class CRoItem(
    @SerializedName("raPvn")
    val aoPvn: List<CRaPvn>,
    @SerializedName("raPvnLng")
    val aoPvnLng: List<CRaPvnLng>
)