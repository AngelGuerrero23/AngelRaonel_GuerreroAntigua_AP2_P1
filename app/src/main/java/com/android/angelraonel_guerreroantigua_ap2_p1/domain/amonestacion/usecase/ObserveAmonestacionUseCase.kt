package com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.usecase

import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.model.Amonestacion
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.repository.AmonestacionRepository
import kotlinx.coroutines.flow.Flow

class ObserveAmonestacionUseCase(
    private var repository: AmonestacionRepository
){
    operator fun invoke(): Flow<List<Amonestacion>> = repository.observeAmonestacion()

}