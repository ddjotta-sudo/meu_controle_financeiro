package com.exemplo.fluxocaixa.data.repository

import com.exemplo.fluxocaixa.data.local.LocalDataSource
import com.exemplo.fluxocaixa.domain.model.Transaction
import com.exemplo.fluxocaixa.domain.repository.CashflowRepository
import com.exemplo.fluxocaixa.domain.usecase.AddTransactionUseCase
import com.exemplo.fluxocaixa.domain.usecase.GetCashflowSummaryUseCase

class CashflowRepositoryImpl(
    private val localDataSource: LocalDataSource
) : CashflowRepository {

    override fun addTransaction(transaction: Transaction): Transaction {
        return localDataSource.insertTransaction(transaction)
    }

    override fun getCashflowSummary(period: String): CashflowSummary {
        return localDataSource.getCashflowSummary(period)
    }

    // Implement other repository methods as needed
}