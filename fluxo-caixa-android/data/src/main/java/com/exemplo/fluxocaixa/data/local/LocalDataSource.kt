package com.exemplo.fluxocaixa.data.local

import com.exemplo.fluxocaixa.domain.model.Transaction

interface LocalDataSource {
    suspend fun insertTransaction(transaction: Transaction): Long
    suspend fun getTransactionById(id: Long): Transaction?
    suspend fun getAllTransactions(): List<Transaction>
    suspend fun deleteTransaction(transaction: Transaction)
    suspend fun updateTransaction(transaction: Transaction)
}