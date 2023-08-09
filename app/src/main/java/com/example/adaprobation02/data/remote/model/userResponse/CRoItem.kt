package com.example.adaprobation02.data.remote.model.userResponse


import com.google.gson.annotations.SerializedName

data class CRoItem(
    @SerializedName("raComp")
    val aoComp: List<CRaComp>,
    @SerializedName("raCompLng")
    val aoCompLng: List<CRaCompLng>,
    @SerializedName("raImage")
    val aoImage: List<CRaImage>,
    @SerializedName("raUrlObject")
    val oUrlObject: Any
)