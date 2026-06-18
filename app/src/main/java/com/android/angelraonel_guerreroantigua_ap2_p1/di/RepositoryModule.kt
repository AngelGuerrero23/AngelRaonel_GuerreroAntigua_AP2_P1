package com.android.angelraonel_guerreroantigua_ap2_p1.di

import com.android.angelraonel_guerreroantigua_ap2_p1.data.amonestacion.repository.AmonestacionRepositoryImpl
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.repository.AmonestacionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAmonestacionRepository(
        amonestacionRepositoryImpl: AmonestacionRepositoryImpl
    ): AmonestacionRepository
}
