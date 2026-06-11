package com.android.angelraonel_guerreroantigua_ap2_p1.data.amonestacion.repository

import com.android.angelraonel_guerreroantigua_ap2_p1.data.amonestacion.local.AmonestacionDao
import com.android.angelraonel_guerreroantigua_ap2_p1.data.amonestacion.mapper.toDomain
import com.android.angelraonel_guerreroantigua_ap2_p1.data.amonestacion.mapper.toEntity
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.model.Amonestacion
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.repository.AmonestacionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

abstract class AmonestacionRepositoryImpl(
    private val dao: AmonestacionDao
): AmonestacionRepository{

    override fun observeAmonestacion(): Flow<List<Amonestacion>> =
        dao.obtenerTodosLosAmonestados().map { list->
            list.map { it.toDomain() }
        }

    override suspend fun getAmonestacion(id: Int): Amonestacion? {
        return dao.obtenerChequePorId(id)?.toDomain()
    }

    override suspend fun upsert(amonestacion: Amonestacion): Int {
        return dao.upsert(amonestacion.toEntity()).toInt()
    }

    suspend fun delete(id: Int) {
        dao.delete(id)
    }

    override suspend fun exists(id: Int): Boolean {
        TODO("Not yet implemented")
    }

}