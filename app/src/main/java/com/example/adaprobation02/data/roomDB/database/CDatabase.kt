package com.example.adaprobation02.data.roomDB.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.adaprobation02.data.roomDB.dao.IQueryDao
import com.example.adaprobation02.data.roomDB.entity.CCNMCompEntity
import com.example.adaprobation02.data.roomDB.entity.CCNMCompLEntity
import com.example.adaprobation02.data.roomDB.entity.CSysSyncDataEntity
import com.example.adaprobation02.data.roomDB.entity.CSysSyncDataLEntity
import kotlinx.coroutines.CoroutineScope

@Database(entities = [CSysSyncDataEntity::class, CSysSyncDataLEntity::class, CCNMCompEntity::class, CCNMCompLEntity::class], version = 1)
abstract class CDatabase : RoomDatabase()
{
    abstract fun IQueryDao():IQueryDao


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: CDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): CDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CDatabase::class.java,
                    "database-name"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(DatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class DatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // call your copy logic here
            }
        }
    }

}


