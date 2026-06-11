package com.android.angelraonel_guerreroantigua_ap2_p1.data.database

import androidx.room.Database
import androidx.room.Query
import androidx.room.RoomDatabase
import com.android.angelraonel_guerreroantigua_ap2_p1.data.amonestacion.local.AmonestacionDao
import com.android.angelraonel_guerreroantigua_ap2_p1.data.amonestacion.local.AmonestacionEntity

@Database(
    entities = [
        AmonestacionEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class RegistroDb : RoomDatabase(){
    abstract fun AmonestacionDao(): AmonestacionDao
}