package com.exemplo.fluxocaixa.data.remote

import com.exemplo.fluxocaixa.domain.model.Transaction
import com.exemplo.fluxocaixa.domain.repository.CashflowRepository
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RemoteDataSource {

    @POST("transactions")
    suspend fun addTransaction(@Body transaction: Transaction): Response<Transaction>

    // Adicione outros métodos de API conforme necessário
}