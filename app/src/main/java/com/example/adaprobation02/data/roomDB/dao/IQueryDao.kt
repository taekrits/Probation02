package com.example.adaprobation02.data.roomDB.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.adaprobation02.data.model.CDataDownloadListDB
import kotlinx.coroutines.flow.Flow

@Dao
interface IQueryDao {

    @Query(
        """
            SELECT FTSynName AS tSynName,
                FDSynLast AS tSynLast, 
                FTSynUriDwn AS tSynUriDwn FROM TSysSyncData AS T1
                JOIN TSysSyncData_L AS T2 ON T1.FNSynSeqNo = T2.FNSynSeqNo
                WHERE T2.FNLngID='1';
            """
    )
    fun C_GETxDownloadList(): Flow<List<CDataDownloadListDB>>

}