package com.example.adaprobation02.data.model

import com.google.gson.annotations.SerializedName

data class CDataDownloadListDB(
    // FTSynName, FDSynLast, FTSynUriDwn
    @SerializedName("FTSynName")
    val tSynName: String,

    @SerializedName("FDSynLast")
    val tSynLast: String,

    @SerializedName("FTSynUriDwn")
    val tSynUriDwn: String
)
