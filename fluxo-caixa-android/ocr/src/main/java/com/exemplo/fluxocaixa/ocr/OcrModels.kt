package com.exemplo.fluxocaixa.ocr

data class OcrResult(
    val text: String,
    val confidence: Float
)

data class OcrRequest(
    val imageUri: String
)

data class OcrResponse(
    val result: OcrResult,
    val timestamp: Long
)