package com.exemplo.fluxocaixa.data.mapper

import com.exemplo.fluxocaixa.domain.model.Transaction
import com.exemplo.fluxocaixa.data.local.entity.TransactionEntity

object TransactionMapper {

    fun toDomain(transactionEntity: TransactionEntity): Transaction {
        return Transaction(
            id = transactionEntity.id,
            dateTime = transactionEntity.dateTime,
            type = transactionEntity.type,
            grossAmount = transactionEntity.grossAmount,
            netAmount = transactionEntity.netAmount,
            currency = transactionEntity.currency,
            categoryId = transactionEntity.categoryId,
            costCenterId = transactionEntity.costCenterId,
            paymentMethod = transactionEntity.paymentMethod,
            note = transactionEntity.note,
            status = transactionEntity.status,
            attachmentsJson = transactionEntity.attachmentsJson,
            feeApplied = transactionEntity.feeApplied,
            feePercent = transactionEntity.feePercent,
            boletoLine = transactionEntity.boletoLine
        )
    }

    fun toEntity(transaction: Transaction): TransactionEntity {
        return TransactionEntity(
            id = transaction.id,
            dateTime = transaction.dateTime,
            type = transaction.type,
            grossAmount = transaction.grossAmount,
            netAmount = transaction.netAmount,
            currency = transaction.currency,
            categoryId = transaction.categoryId,
            costCenterId = transaction.costCenterId,
            paymentMethod = transaction.paymentMethod,
            note = transaction.note,
            status = transaction.status,
            attachmentsJson = transaction.attachmentsJson,
            feeApplied = transaction.feeApplied,
            feePercent = transaction.feePercent,
            boletoLine = transaction.boletoLine
        )
    }
}