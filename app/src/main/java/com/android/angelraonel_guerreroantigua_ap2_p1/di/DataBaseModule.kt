package com.android.angelraonel_guerreroantigua_ap2_p1.di

import android.content.Context
import androidx.room.Room
import com.android.angelraonel_guerreroantigua_ap2_p1.data.amonestacion.local.AmonestacionDao
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
    fun provideAmonestacionDb(@ApplicationContext context: Context): RegistroDb{
        return Room.databaseBuilder(
                context,
                RegistroDb::class.java,
                "amonestacion_db",

        ).fallbackToDestructiveMigration().build()
    }


    @Provides
    @Singleton
    fun provideAmonestacionDao(database: RegistroDb): AmonestacionDao{
        return database.AmonestacionDao()
    }

}