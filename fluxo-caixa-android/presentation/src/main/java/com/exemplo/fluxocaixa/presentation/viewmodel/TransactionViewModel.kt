package com.exemplo.fluxocaixa.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exemplo.fluxocaixa.domain.model.Transaction
import com.exemplo.fluxocaixa.domain.usecase.AddTransactionUseCase
import com.exemplo.fluxocaixa.domain.usecase.GetCashflowSummaryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TransactionViewModel(
    private val addTransactionUseCase: AddTransactionUseCase,
    private val getCashflowSummaryUseCase: GetCashflowSummaryUseCase
) : ViewModel() {

    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> = _transactions

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            val newTransaction = addTransactionUseCase.execute(transaction)
            _transactions.value = _transactions.value + newTransaction
        }
    }

    fun getCashflowSummary(period: String) {
        viewModelScope.launch {
            // Implementar lógica para obter o resumo do fluxo de caixa
        }
    }
}