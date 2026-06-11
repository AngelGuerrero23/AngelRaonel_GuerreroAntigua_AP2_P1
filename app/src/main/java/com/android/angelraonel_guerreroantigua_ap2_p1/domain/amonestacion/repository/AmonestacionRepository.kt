package com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.repository

import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.model.Amonestacion
import kotlinx.coroutines.flow.Flow

interface AmonestacionRepository {
    fun observeAmonestacion(): Flow<List<Amonestacion>>
    suspend fun getAmonestacion(id: Int): Amonestacion?
    suspend fun upsert(amonestacion: Amonestacion): Int
    suspend fun deleteAmonestacion(id: Int)
    suspend fun exists(id: Int): Boolean
}