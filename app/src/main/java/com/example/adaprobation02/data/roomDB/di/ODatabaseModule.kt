package com.example.adaprobation02.data.roomDB.di

import android.content.Context
import com.example.adaprobation02.data.roomDB.dao.IQueryDao
import com.example.adaprobation02.data.roomDB.database.CDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ODatabaseModule {
//    @Provides
//    fun provideDatabase(@ApplicationContext context: Context):CDatabase{
//        //สร้าง instant
//        return  Room.databaseBuilder(
//            context,
//            CDatabase::class.java, "database.db"
//        ).fallbackToDestructiveMigration()
//            .build()
//    }
//

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): CDatabase {
        return CDatabase.getDatabase(appContext, CoroutineScope(Dispatchers.IO))
    }

    @Provides
    fun provideIQueryDao(database: CDatabase): IQueryDao {
        return database.IQueryDao()
    }




}