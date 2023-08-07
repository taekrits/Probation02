package com.example.adaprobation02.data.helper

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.adaprobation02.data.model.CDataDownloadListDB
import com.example.adaprobation02.data.repository.ISyncDataRepository
import com.google.gson.Gson
import java.io.File


class CDatabaseHelper(context: Context) : ISyncDataRepository {
    val dbPath = context.getDatabasePath("SyncData.db").absolutePath
    fun C_SETxOpenDatabase(): SQLiteDatabase {
        val file = File(dbPath)
        if (!file.exists()) {
            Log.e("Database Error", "File does not exist!")
        }
        return SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE)
    }

    fun C_GETxQueryData(query: String): Cursor {
        val db = C_SETxOpenDatabase()
        return db.rawQuery(query, null)
    }

    private fun C_SETtCursorToJson(cursor: Cursor): String {
        val json = StringBuilder("{")
        for (colIndex in 0 until cursor.columnCount) {
            json.append("\"${cursor.getColumnName(colIndex)}\": \"${cursor.getString(colIndex)}\"")
            if (colIndex < cursor.columnCount - 1) json.append(", ")
        }
        json.append("}")
        return json.toString()
    }

    override fun C_GETxTaskDataList(): List<CDataDownloadListDB> {
        val values = mutableListOf<CDataDownloadListDB>()
        val gson = Gson()
        val query =
            """
                SELECT FTSynName, FDSynLast, FTSynUriDwn
                FROM TSysSyncData AS T1
                JOIN TSysSyncData_L AS T2 ON T1.FNSynSeqno = T2.FNSynSeqno
                WHERE T2.FNLngID='1';
            """
        val cursor = C_GETxQueryData(query)

        while (cursor.moveToNext()) {
            val json = C_SETtCursorToJson(cursor)
            val value: CDataDownloadListDB = gson.fromJson(json, CDataDownloadListDB::class.java)
            values.add(value)
        }
        cursor.close()

        return values
    }
}

