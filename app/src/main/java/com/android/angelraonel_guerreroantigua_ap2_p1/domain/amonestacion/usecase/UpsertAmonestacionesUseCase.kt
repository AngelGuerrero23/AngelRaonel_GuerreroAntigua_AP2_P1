package com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.usecase

import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.model.Amonestacion
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.repository.AmonestacionRepository
import javax.inject.Inject

class UpsertAmonestacionesUseCase @Inject constructor(
    private val repository: AmonestacionRepository
) {
    suspend operator fun invoke(amonestacion: Amonestacion): Result<String> {
        val amonestacionId = validateAmonestacion(amonestacion.amonestacionId)
        if(!amonestacionId.isValid){
            return Result.failure(IllegalArgumentException(amonestacionId.error))
        }
        val nombreResult = validateNombres(amonestacion.nombre.toString())
        if (!nombreResult.isValid) {
            return Result.failure(IllegalArgumentException(nombreResult.error))
        }
        val razonResult = validateRazon(amonestacion.razon.toString())
        if(!razonResult.isValid){
            return Result.failure(IllegalArgumentException(razonResult.error))
        }
        val montoResult = validateMonto(amonestacion.monto.toString())
        if (!montoResult.isValid) {
            return Result.failure(IllegalArgumentException(montoResult.error))
        }
        return runCatching { repository.upsert(amonestacion).toString() }
    }
}
