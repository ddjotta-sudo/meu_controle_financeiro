package com.exemplo.fluxocaixa.domain.usecase

import com.exemplo.fluxocaixa.domain.model.Transaction
import com.exemplo.fluxocaixa.domain.repository.CashflowRepository

class AddTransactionUseCase(private val repository: CashflowRepository) {
    fun execute(transaction: Transaction): Transaction {
        return repository.addTransaction(transaction)
    }
}