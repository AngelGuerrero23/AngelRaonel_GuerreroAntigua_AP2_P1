package com.android.angelraonel_guerreroantigua_ap2_p1.data.mapper

import com.android.angelraonel_guerreroantigua_ap2_p1.data.amonestacion.local.AmonestacionEntity
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.model.Amonestacion


fun AmonestacionEntity.toDomain() = Amonestacion(
    amonestacionId = amonestacionId,
    nombre = nombre,
    razon = razon,
    monto= monto
)

fun Amonestacion.toEntity() = AmonestacionEntity(
    amonestacionId = amonestacionId,
    nombre = nombre,
    razon =razon,
    monto = monto
)