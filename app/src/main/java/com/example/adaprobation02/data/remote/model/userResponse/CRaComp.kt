package com.example.adaprobation02.data.remote.model.userResponse


import com.google.gson.annotations.SerializedName

data class CRaComp(
    @SerializedName("rdCreateOn")
    val tCreateOn: String,
    @SerializedName("rdLastUpdOn")
    val tLastUpdOn: String,
    @SerializedName("rtBchcode")
    val tBchcode: String,
    @SerializedName("rtCmpCode")
    val tCmpCode: String,
    @SerializedName("rtCmpEmail")
    val tCmpEmail: String,
    @SerializedName("rtCmpFax")
    val tCmpFax: String,
    @SerializedName("rtCmpRetInOrEx")
    val tCmpRetInOrEx: String,
    @SerializedName("rtCmpTel")
    val tCmpTel: String,
    @SerializedName("rtCmpWhsInOrEx")
    val tCmpWhsInOrEx: String,
    @SerializedName("rtCreateBy")
    val tCreateBy: String,
    @SerializedName("rtLastUpdBy")
    val tLastUpdBy: String,
    @SerializedName("rtRteCode")
    val tRteCode: String,
    @SerializedName("rtVatCode")
    val tVatCode: String
)