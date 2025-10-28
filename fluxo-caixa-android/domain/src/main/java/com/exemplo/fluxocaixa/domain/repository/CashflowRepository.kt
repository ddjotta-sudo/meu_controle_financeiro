package com.exemplo.fluxocaixa.domain.repository

import com.exemplo.fluxocaixa.domain.model.Transaction

interface CashflowRepository {
    suspend fun addTransaction(transaction: Transaction): Transaction
    suspend fun getCashflowSummary(period: String): CashflowSummary
    suspend fun getAllTransactions(): List<Transaction>
    suspend fun getTransactionById(id: Long): Transaction?
    suspend fun deleteTransaction(id: Long)
}