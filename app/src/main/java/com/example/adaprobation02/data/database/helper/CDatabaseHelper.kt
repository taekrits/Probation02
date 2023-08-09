package com.example.adaprobation02.data.database.helper

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.adaprobation02.data.database.model.CCNMComp
import com.example.adaprobation02.data.database.model.CCNMCompL
import com.example.adaprobation02.data.database.model.CCNMDistrict
import com.example.adaprobation02.data.database.model.CCNMDistrictL
import com.example.adaprobation02.data.database.model.CCNMPovince
import com.example.adaprobation02.data.database.model.CCNMPovinceL
import com.example.adaprobation02.data.database.model.CDataDownloadListDB
import com.example.adaprobation02.data.database.repository.ISyncDataRepository
import com.google.gson.Gson
import java.io.File


class CDatabaseHelper(context: Context) : ISyncDataRepository {
    val oC_dbPath = context.getDatabasePath("SyncData.db").absolutePath
    fun C_SETxOpenDatabase(): SQLiteDatabase {
        val oFile = File(oC_dbPath)
        if (!oFile.exists()) {
            Log.e("Database Error", "File does not exist!")
        }
        return SQLiteDatabase.openDatabase(oC_dbPath, null, SQLiteDatabase.OPEN_READWRITE)
    }

    fun C_GETxQueryData(ptQuery: String): Cursor {
        val oDb = C_SETxOpenDatabase()
        return oDb.rawQuery(ptQuery, null)
    }

    private fun C_SETtCursorToJson(poCursor: Cursor): String {
        val oJson = StringBuilder("{")
        for (colIndex in 0 until poCursor.columnCount) {
            oJson.append("\"${poCursor.getColumnName(colIndex)}\": \"${poCursor.getString(colIndex)}\"")
            if (colIndex < poCursor.columnCount - 1) oJson.append(", ")
        }
        oJson.append("}")
        return oJson.toString()
    }

    override fun C_GETxTaskDataList(): List<CDataDownloadListDB> {
        val aoValues = mutableListOf<CDataDownloadListDB>()
        val gson = Gson()
        val tQuery =
            """
                SELECT FTSynName, FDSynLast, FTSynUriDwn
                FROM TSysSyncData AS T1
                JOIN TSysSyncData_L AS T2 ON T1.FNSynSeqno = T2.FNSynSeqno
                WHERE T2.FNLngID='1';
            """
        val oCursor = C_GETxQueryData(tQuery)

        while (oCursor.moveToNext()) {
            val oJson = C_SETtCursorToJson(oCursor)
            val oValue: CDataDownloadListDB = gson.fromJson(oJson, CDataDownloadListDB::class.java)
            aoValues.add(oValue)
        }
        oCursor.close()

        return aoValues
    }

    fun C_SETxDelete() {
        val oDb = C_SETxOpenDatabase()
        oDb.beginTransaction()
        try {
            val tDeleteCCNMCompL = "DELETE FROM TCNMComp_L"
            oDb.execSQL(tDeleteCCNMCompL)
            val tDeleteCCNMComp = "DELETE FROM TCNMComp"
            oDb.execSQL(tDeleteCCNMComp)
            val tDeleteCCNMProvince = "DELETE FROM TCNMProvince"
            oDb.execSQL(tDeleteCCNMProvince)
            val tDeleteCCNMProvinceL = "DELETE FROM TCNMProvince_L"
            oDb.execSQL(tDeleteCCNMProvinceL)
            val tDeleteCCNMDistrict = "DELETE FROM TCNMDistrict"
            oDb.execSQL(tDeleteCCNMDistrict)
            val tDeleteCCNMDistrictL = "DELETE FROM TCNMDistrict_L"
            oDb.execSQL(tDeleteCCNMDistrictL)


            oDb.setTransactionSuccessful()
        } catch (e: Exception) {
            Log.e("Database Error", "Error occurred while deleting data", e)
        } finally {
            oDb.endTransaction()
        }
    }


     fun C_SETxInserCCNMCompL(paData: List<CCNMCompL>) {
        val oDb = C_SETxOpenDatabase()
        oDb.beginTransaction()
        try {
            val tSql = "INSERT OR REPLACE INTO TCNMComp_L (FTCmpCode, FNLngID, FTCmpName, FTCmpShop, FTCmpDirector) VALUES (?, ?, ?, ?, ?)"
            val oStmt = oDb.compileStatement(tSql)

            for (item in paData) {
                oStmt.bindString(1, item.tCmpCode)
                oStmt.bindLong(2, item.tLngID.toLong())
                oStmt.bindString(3, item.tCmpName)
                oStmt.bindString(4, item.tCmpShop)
                oStmt.bindString(5, item.tCmpDirector)

                oStmt.execute()
                oStmt.clearBindings()
            }

            oDb.setTransactionSuccessful()
        } catch (e: Exception) {
            Log.e("Database Error", "Error occurred while inserting data", e)
        } finally {
            oDb.endTransaction()
        }
    }

     fun C_SETxInsertCCNMComp(data: List<CCNMComp>) {
        val oDb = C_SETxOpenDatabase()
        oDb.beginTransaction()
        try {
            val tSql =
                """
            INSERT OR REPLACE INTO TCNMComp (FTCmpCode, FTCmpTel, FTCmpFax, FTBchcode, FTCmpWhsInOrEx, FTCmpRetInOrEx, 
            FTCmpEmail, FTRteCode, FTVatCode, FDLastUpdOn, FTLastUpdBy, FDCreateOn, FTCreateBy) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """
            val oStmt = oDb.compileStatement(tSql)

            for (item in data) {
                oStmt.bindString(1, item.tCmpCode)
                oStmt.bindString(2, item.tCmpTel ?: "")
                oStmt.bindString(3, item.tCmpFax ?: "")
                oStmt.bindString(4, item.tBchcode ?: "")
                oStmt.bindString(5, item.tCmpWhsInOrEx ?: "")
                oStmt.bindString(6, item.tCmpRetInOrEx ?: "")
                oStmt.bindString(7, item.tCmpEmail ?: "")
                oStmt.bindString(8, item.tRteCode)
                oStmt.bindString(9, item.tVatCode)
                oStmt.bindString(10, item.tLastUpdOn ?: "")
                oStmt.bindString(11, item.tLastUpdBy ?: "")
                oStmt.bindString(12, item.tCreateOn ?: "")
                oStmt.bindString(13, item.tCreateBy ?: "")

                oStmt.execute()
                oStmt.clearBindings()
            }

            oDb.setTransactionSuccessful()
        } catch (e: Exception) {
            Log.e("Database Error", "Error occurred while inserting data", e)
        } finally {
            oDb.endTransaction()
        }
    }
    fun C_SETxInsertCCNMPovince(paData: List<CCNMPovince>) {
        val oDb = C_SETxOpenDatabase()
        oDb.beginTransaction()
        try {
            val tSql = """
            INSERT OR REPLACE INTO TCNMProvince (
                FTPvnCode, FTZneCode, FTPvnLatitude, FTPvnLongitude, 
                FDLastUpdOn, FTLastUpdBy, FDCreateOn, FTCreateBy
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """
            val oStmt = oDb.compileStatement(tSql)

            for (item in paData) {
                oStmt.bindString(1, item.tPvnCode)
                oStmt.bindString(2, item.tZneCode ?: "")
                oStmt.bindString(3, item.tPvnLatitude ?: "")
                oStmt.bindString(4, item.tPvnLongitude ?: "")
                oStmt.bindString(5, item.tLastUpdOn ?: "")
                oStmt.bindString(6, item.tLastUpdBy ?: "")
                oStmt.bindString(7, item.tCreateOn ?: "")
                oStmt.bindString(8, item.tCreateBy ?: "")

                oStmt.execute()
                oStmt.clearBindings()
            }

            oDb.setTransactionSuccessful()
        } catch (e: Exception) {
            Log.e("Database Error", "Error occurred while inserting data", e)
        } finally {
            oDb.endTransaction()
        }
    }
    fun C_SETxInsertCCNMPovinceL(paData: List<CCNMPovinceL>) {
        val oDb = C_SETxOpenDatabase()
        oDb.beginTransaction()
        try {
            val tSql = """
            INSERT OR REPLACE INTO TCNMProvince_L (
                FTPvnCode, FNLngID, FTPvnName
            ) VALUES (?, ?, ?)
        """
            val oStmt = oDb.compileStatement(tSql)

            for (item in paData) {
                oStmt.bindString(1, item.tPvnCode)
                oStmt.bindLong(2, item.nLngID.toLong())
                oStmt.bindString(3, item.tName ?: "")

                oStmt.execute()
                oStmt.clearBindings()
            }

            oDb.setTransactionSuccessful()
        } catch (e: Exception) {
            Log.e("Database Error", "Error occurred while inserting data", e)
        } finally {
            oDb.endTransaction()
        }
    }
    fun C_SETxInsertCCNMDistrict(paData: List<CCNMDistrict>) {
        val oDb = C_SETxOpenDatabase()
        oDb.beginTransaction()
        try {
            val tSql = """
            INSERT OR REPLACE INTO TCNMDistrict (
                FTDstCode, FTDstPost, FTPvnCode, FTDstLatitude, FTDstLongitude,
                FDLastUpdOn, FTLastUpdBy, FDCreateOn, FTCreateBy
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """
            val oStmt = oDb.compileStatement(tSql)

            for (item in paData) {
                oStmt.bindString(1, item.tDstCode)
                oStmt.bindString(2, item.tDstPost ?: "")
                oStmt.bindString(3, item.tPvnCode ?: "")
                oStmt.bindString(4, item.tDstLatitude ?: "")
                oStmt.bindString(5, item.tDstLongitude ?: "")
                oStmt.bindString(6, item.tLastUpdOn ?: "")
                oStmt.bindString(7, item.tLastUpdBy ?: "")
                oStmt.bindString(8, item.tCreateOn ?: "")
                oStmt.bindString(9, item.tCreateBy ?: "")

                oStmt.execute()
                oStmt.clearBindings()
            }

            oDb.setTransactionSuccessful()
        } catch (e: Exception) {
            Log.e("Database Error", "Error occurred while inserting data", e)
        } finally {
            oDb.endTransaction()
        }
    }
    fun C_SETxInsertCCNMDistrictL(paData: List<CCNMDistrictL>) {
        val oDb = C_SETxOpenDatabase()
        oDb.beginTransaction()
        try {
            val tSql = """
            INSERT OR REPLACE INTO TCNMDistrict_L (FTDstCode, FNLngID, FTDstName) 
            VALUES (?, ?, ?)
        """
            val oStmt = oDb.compileStatement(tSql)

            for (item in paData) {
                oStmt.bindString(1, item.tDstCode)
                oStmt.bindLong(2, item.tLngID.toLong())
                oStmt.bindString(3, item.tDstName)

                oStmt.execute()
                oStmt.clearBindings()
            }

            oDb.setTransactionSuccessful()
        } catch (e: Exception) {
            Log.e("Database Error", "Error occurred while inserting data", e)
        } finally {
            oDb.endTransaction()
        }
    }

    fun C_SETxUpdateTSynLast(ptSynLast: String, ptSynUriDwn: String) {
        val oDb = C_SETxOpenDatabase()  // สมมติว่าเป็นฟังก์ชันสำหรับเปิดฐานข้อมูล
        oDb.beginTransaction()
        try {
            val tSql = """
            UPDATE TSysSyncData 
            SET FDSynLast = ?
            WHERE FTSynUriDwn = ? ;
        """
            val oStmt = oDb.compileStatement(tSql)

            oStmt.bindString(1, ptSynLast)
            oStmt.bindString(2, ptSynUriDwn)

            oStmt.executeUpdateDelete() // ใช้ executeUpdateDelete สำหรับการ update แทน execute
            oStmt.clearBindings()

            oDb.setTransactionSuccessful()
        } catch (e: Exception) {
            Log.e("Database Error", "Error occurred while updating FDSynLast", e)
        } finally {
            oDb.endTransaction()
        }
    }
}

