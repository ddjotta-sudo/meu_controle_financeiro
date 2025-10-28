package com.exemplo.fluxocaixa.domain.model

data class Transaction(
    val id: Long,
    val dateTime: Long,
    val type: TransactionType,
    val grossAmount: Double,
    val netAmount: Double,
    val currency: String,
    val categoryId: Long,
    val costCenterId: Long,
    val paymentMethod: String,
    val note: String,
    val status: TransactionStatus,
    val attachmentsJson: String,
    val feeApplied: Boolean,
    val feePercent: Double,
    val boletoLine: String
)

enum class TransactionType {
    INCOME,
    EXPENSE
}

enum class TransactionStatus {
    PAID,
    PENDING,
    OVERDUE
}