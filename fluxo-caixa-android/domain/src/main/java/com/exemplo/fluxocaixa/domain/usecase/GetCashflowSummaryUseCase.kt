package com.exemplo.fluxocaixa.domain.usecase

import com.exemplo.fluxocaixa.domain.model.Transaction
import com.exemplo.fluxocaixa.domain.repository.CashflowRepository

class GetCashflowSummaryUseCase(private val cashflowRepository: CashflowRepository) {

    fun execute(period: String): CashflowSummary {
        // Implementação da lógica para obter o resumo do fluxo de caixa
        val transactions = cashflowRepository.getTransactionsForPeriod(period)
        val totalIncome = transactions.filter { it.type == TransactionType.INCOME }.sumOf { it.netAmount }
        val totalExpenses = transactions.filter { it.type == TransactionType.EXPENSE }.sumOf { it.netAmount }

        return CashflowSummary(totalIncome, totalExpenses)
    }
}

data class CashflowSummary(
    val totalIncome: Double,
    val totalExpenses: Double
)

enum class TransactionType {
    INCOME,
    EXPENSE
}