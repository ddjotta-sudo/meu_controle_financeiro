package com.exemplo.fluxocaixa.ocr

import android.graphics.Bitmap
import android.net.Uri
import com.exemplo.fluxocaixa.ocr.OnDeviceOcrProcessor
import com.exemplo.fluxocaixa.ocr.OcrModels.OcrResult

class OcrManager(private val ocrProcessor: OnDeviceOcrProcessor) {

    fun scanReceipt(imageUri: Uri): OcrResult {
        val bitmap = getBitmapFromUri(imageUri)
        return ocrProcessor.process(bitmap)
    }

    private fun getBitmapFromUri(uri: Uri): Bitmap {
        // Implementar a lógica para converter a URI em um Bitmap
        // Isso pode incluir o uso de ContentResolver e BitmapFactory
    }
}