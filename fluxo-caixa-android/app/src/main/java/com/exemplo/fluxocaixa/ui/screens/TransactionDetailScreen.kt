package com.exemplo.fluxocaixa.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.exemplo.fluxocaixa.presentation.viewmodel.TransactionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionDetailScreen(
    transactionId: Long,
    viewModel: TransactionViewModel = hiltViewModel()
) {
    val transaction = viewModel.getTransactionById(transactionId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhes da Transação") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            transaction?.let {
                Text(text = "Data: ${it.dateTime}")
                Text(text = "Tipo: ${it.type}")
                Text(text = "Valor Bruto: ${it.grossAmount}")
                Text(text = "Valor Líquido: ${it.netAmount}")
                Text(text = "Moeda: ${it.currency}")
                Text(text = "Método de Pagamento: ${it.paymentMethod}")
                Text(text = "Nota: ${it.note}")
                Text(text = "Status: ${it.status}")
                // Adicione mais campos conforme necessário
            } ?: run {
                Text(text = "Transação não encontrada.")
            }
        }
    }
}