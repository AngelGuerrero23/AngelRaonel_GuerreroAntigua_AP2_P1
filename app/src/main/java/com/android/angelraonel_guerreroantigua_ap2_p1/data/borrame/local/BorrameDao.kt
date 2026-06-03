package com.android.angelraonel_guerreroantigua_ap2_p1.data.borrame.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface BorrameDao {
    @Upsert
    suspend fun upsert(entity: BorrameEntity)
}