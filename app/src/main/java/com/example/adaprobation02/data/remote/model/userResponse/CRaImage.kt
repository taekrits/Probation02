package com.example.adaprobation02.data.remote.model.userResponse


import com.google.gson.annotations.SerializedName

data class CRaImage(
    @SerializedName("rdCreateOn")
    val tCreateOn: String,
    @SerializedName("rdLastUpdOn")
    val tLastUpdOn: String,
    @SerializedName("rnImgID")
    val nImgID: Int,
    @SerializedName("rnImgSeq")
    val nImgSeq: Int,
    @SerializedName("rtCreateBy")
    val tCreateBy: String,
    @SerializedName("rtImgKey")
    val tImgKey: String,
    @SerializedName("rtImgObj")
    val tImgObj: String,
    @SerializedName("rtImgRefID")
    val tImgRefID: String,
    @SerializedName("rtImgTable")
    val tImgTable: String,
    @SerializedName("rtLastUpdBy")
    val tLastUpdBy: String
)