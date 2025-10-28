package com.exemplo.fluxocaixa.util

import android.content.Context
import android.net.Uri
import android.widget.Toast

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Uri.isImage(): Boolean {
    val mimeType = this.toString().substringAfterLast(".")
    return mimeType in listOf("jpg", "jpeg", "png", "gif")
}