package com.android.angelraonel_guerreroantigua_ap2_p1.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.angelraonel_guerreroantigua_ap2_p1.data.borrame.local.BorrameDao
import com.android.angelraonel_guerreroantigua_ap2_p1.data.borrame.local.BorrameEntity

@Database(
    entities = [
        BorrameEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class RegistroDb : RoomDatabase(){
    abstract fun borrameDao(): BorrameDao
}