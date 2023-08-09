package com.example.adaprobation02.data.remote.model.districtResponse


import com.google.gson.annotations.SerializedName

data class CRoItem(
    @SerializedName("raDistrinct")
    val aoDistrinct: List<CRaDistrinct>,
    @SerializedName("raDistrinctLng")
    val aoDistrinctLng: List<CRaDistrinctLng>
)