package com.android.angelraonel_guerreroantigua_ap2_p1.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.android.angelraonel_guerreroantigua_ap2_p1.data.borrame.local.BorrameDao
import com.android.angelraonel_guerreroantigua_ap2_p1.data.database.RegistroDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideBorrameDb(@ApplicationContext context: Context): RegistroDb{
        return Room.databaseBuilder(
                context,
                RegistroDb::class.java,
                "empleado_db",

        ).fallbackToDestructiveMigration().build()
    }


    @Provides
    @Singleton
    fun provideBorrameDao(database: RegistroDb): BorrameDao{
        return database.borrameDao()
    }

}