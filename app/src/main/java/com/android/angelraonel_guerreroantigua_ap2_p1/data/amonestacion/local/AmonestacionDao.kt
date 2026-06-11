package com.android.angelraonel_guerreroantigua_ap2_p1.data.amonestacion.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.model.Amonestacion
import kotlinx.coroutines.flow.Flow

@Dao
interface AmonestacionDao {
    @Upsert
    suspend fun upsert(amonestacionEntity: AmonestacionEntity): Long

    @Query("SELECT * FROM amonestacion_table WHERE amonestacionId= :id")
    suspend fun obtenerChequePorId(id: Int): AmonestacionEntity?

    @Query("SELECT * FROM amonestacion_table")
    fun obtenerTodosLosAmonestados(): Flow<List<AmonestacionEntity>>

    @Query("DELETE FROM amonestacion_table WHERE amonestacionId = :id")
    suspend fun delete(id: Int)
}