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

    @Delete
    suspend fun delete(entity: BorrameEntity)

    @Query("Select * from borrame ORDER BY borrameId")
    fun observeAll(): Flow<List<BorrameEntity>>

    @Query("Select * from borrame WHERE borrameId=:id")
    suspend fun getById(id: Int): BorrameEntity

    @Query("Delete from borrame WHERE borrameId=:id")
    suspend fun deleteById(id: Int)

    @Query("Select exists(Select 1 from borrame WHERE borrameId=:id)")
    suspend fun exists(id: Int): Boolean
}