package com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.usecase

import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.model.Amonestacion
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.repository.AmonestacionRepository

class UpsertAmonestacionesUseCase (
    private var repository: AmonestacionRepository
) {
    suspend operator fun invoke(amonestacion: Amonestacion): Result<String> {
        val beneficiarioResult = validateNombre(amonestacion.nombre.toString())
        if (!beneficiarioResult.isValid) {
            return Result.failure(IllegalArgumentException(beneficiarioResult.error))
        }
        val montoResult = validateMonto(amonestacion.monto.toString())
        if (!montoResult.isValid) {
            return Result.failure(IllegalArgumentException(montoResult.error))
        }
        return runCatching { repository.upsert(amonestacion).toString() }
    }
}
