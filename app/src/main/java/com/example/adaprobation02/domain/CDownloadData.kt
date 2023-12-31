package com.example.adaprobation02.domain

import androidx.compose.runtime.MutableState

data class CDownloadData(
    var tSearchEditText: String = "",
    var bSelectAll: Boolean = false,
    val bSyncData: Boolean = false,
    val bClearOldData: Boolean = false,
    val tDateEditText: String = "",
    val aoDataDownloadList: List<CDownloadList> = listOf(),
    val bShowingOldDataLine: Boolean = true
)

data class CDownloadList(
    var bSelect: MutableState<Boolean>,
    val tName: String,
    var tDateTime: String,
    val tUri: String
)

