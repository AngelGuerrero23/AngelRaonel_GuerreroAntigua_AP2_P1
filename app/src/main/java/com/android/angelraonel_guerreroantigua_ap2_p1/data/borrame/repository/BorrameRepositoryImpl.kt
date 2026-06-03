package com.android.angelraonel_guerreroantigua_ap2_p1.data.borrame.repository

import com.android.angelraonel_guerreroantigua_ap2_p1.data.borrame.local.BorrameDao
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.borrame.model.Borrame
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.borrame.repository.BorrameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BorrameRepositoryImpl @Inject constructor(
    private val dao: BorrameDao
): BorrameRepository{
    override fun observeBorrame(): Flow<List<Borrame>> {
        TODO("Not yet implemented")
    }

    override suspend fun getBorrame(id: Int): Borrame? {
        TODO("Not yet implemented")
    }

    override suspend fun upsert(borrame: Borrame): Int {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun exists(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}
