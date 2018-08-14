package com.kotlinandroidstarter.app.utils

import android.content.Context
import android.support.v7.app.AlertDialog

class ConfirmDialog(context: Context, title: String, message: String, onYes: () -> Unit) {
    
    init {
        
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setNegativeButton("No", null)
            .setPositiveButton("Yes") { _, _ -> onYes() }
            .show()
        
    }
    
}
