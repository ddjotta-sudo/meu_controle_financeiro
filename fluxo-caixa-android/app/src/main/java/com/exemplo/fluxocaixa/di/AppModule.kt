package com.exemplo.fluxocaixa.di

import android.content.Context
import com.exemplo.fluxocaixa.data.local.LocalDataSource
import com.exemplo.fluxocaixa.data.local.db.AppDatabase
import com.exemplo.fluxocaixa.data.repository.CashflowRepositoryImpl
import com.exemplo.fluxocaixa.domain.repository.CashflowRepository
import com.exemplo.fluxocaixa.ocr.OnDeviceOcrProcessor
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindCashflowRepository(
        repositoryImpl: CashflowRepositoryImpl
    ): CashflowRepository

    companion object {
        @Provides
        @Singleton
        fun provideDatabase(context: Context): AppDatabase {
            return AppDatabase.getInstance(context)
        }

        @Provides
        @Singleton
        fun provideLocalDataSource(database: AppDatabase): LocalDataSource {
            return LocalDataSource(database.transactionDao())
        }

        @Provides
        @Singleton
        fun provideOcrProcessor(): OnDeviceOcrProcessor {
            return OnDeviceOcrProcessor()
        }
    }
}