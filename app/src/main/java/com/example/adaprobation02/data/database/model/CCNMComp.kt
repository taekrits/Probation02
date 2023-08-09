package com.example.adaprobation02.data.database.model

import com.google.gson.annotations.SerializedName

data class CCNMComp(
    @SerializedName("FTCmpCode")
    val tCmpCode: String,
    @SerializedName("FTCmpTel")
    val tCmpTel: String?,
    @SerializedName("FTCmpFax")
    val tCmpFax: String?,
    @SerializedName("FTBchcode")
    val tBchcode: String?,
    @SerializedName("FTCmpWhsInOrEx")
    val tCmpWhsInOrEx: String?,
    @SerializedName("FTCmpRetInOrEx")
    val tCmpRetInOrEx: String?,
    @SerializedName("FTCmpEmail")
    val tCmpEmail: String?,
    @SerializedName("FTRteCode")
    val tRteCode: String,
    @SerializedName("FTVatCode")
    val tVatCode: String,
    @SerializedName("FDLastUpdOn")
    val tLastUpdOn: String?,
    @SerializedName("FTLastUpdBy")
    val tLastUpdBy: String?,
    @SerializedName("FDCreateOn")
    val tCreateOn: String?,
    @SerializedName("FTCreateBy")
    val tCreateBy: String?
)

