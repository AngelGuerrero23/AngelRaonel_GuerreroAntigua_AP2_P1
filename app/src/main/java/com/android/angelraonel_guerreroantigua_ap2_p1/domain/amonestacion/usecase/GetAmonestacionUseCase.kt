package com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.usecase

import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.repository.AmonestacionRepository
import javax.inject.Inject

class GetAmonestacionUseCase @Inject constructor(
private val repository: AmonestacionRepository
){
    suspend operator fun invoke(id: Int) = repository.getAmonestacion(id)
}