package com.exemplo.fluxocaixa.ocr

import android.graphics.Bitmap
import android.graphics.ImageFormat
import android.media.Image
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import kotlinx.coroutines.tasks.await

class OnDeviceOcrProcessor {

    private val textRecognizer: TextRecognizer = TextRecognition.getClient()

    suspend fun processImage(imageProxy: ImageProxy): String {
        val bitmap = imageProxy.toBitmap() ?: return ""
        val inputImage = InputImage.fromBitmap(bitmap, imageProxy.imageInfo.rotationDegrees)
        return recognizeText(inputImage)
    }

    private suspend fun recognizeText(image: InputImage): String {
        return try {
            val result = textRecognizer.process(image).await()
            result.text
        } catch (e: Exception) {
            ""
        }
    }

    private fun ImageProxy.toBitmap(): Bitmap? {
        val buffer = this.planes[0].buffer
        val bytes = ByteArray(buffer.remaining())
        buffer.get(bytes)
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }

    fun close() {
        textRecognizer.close()
    }
}